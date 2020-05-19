package com.app4funbr.bank.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.app4funbr.bank.R
import com.app4funbr.bank.infrastructure.extensions.showPwdStrength
import com.app4funbr.bank.infrastructure.extensions.showSnackBar
import com.app4funbr.bank.infrastructure.util.CPFUtil
import com.app4funbr.bank.infrastructure.util.EditTextUtils
import com.app4funbr.bank.infrastructure.util.Utils
import com.app4funbr.bank.model.AccountRequest
import com.app4funbr.bank.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var pDialog: ProgressDialog

    private var currentOption = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        observeViewModel(view)

        button_login?.setOnClickListener {
            doLogin()
        }

        radio_group?.setOnCheckedChangeListener { group, checkedId ->
            text_label_auth_method?.visibility = View.GONE
            radio_group?.visibility = View.GONE
            linear_fields?.visibility = View.VISIBLE
            button_login?.visibility = View.VISIBLE
            setupUserField()
        }
    }

    private fun setupUserField() {
        val selectedId = radio_group.checkedRadioButtonId
        when (selectedId) {
            R.id.rb_user -> {
                currentOption = "email"
                edit_user?.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }

            R.id.rb_cpf -> {
                currentOption = "cpf"
                edit_user?.inputType = InputType.TYPE_CLASS_NUMBER
                edit_user?.addTextChangedListener(EditTextUtils.mask("###.###.###-##", edit_user))
            }
        }
    }

    private fun doLogin() {
        val user = edit_user.text.toString()
        val pwd = edit_pwd.text.toString()

        if (!user.isNullOrEmpty() && !pwd.isNullOrEmpty()) {
            //Verifica a força da senha, se for menor que 4, exibe um aviso com dicas de senha
            val strength = Utils.getStregthPwd(pwd)
            if (!strength.equals("4")) {
                showPwdStrength(requireContext(), strength)
            } else {
                val request = AccountRequest(user, pwd)
                // Verifica se for o tipo CPF selecionado
                if (currentOption.equals("cpf")) {
                    val isValid = CPFUtil.myValidateCPF(user)
                    //Verifica se o CPF é válido
                    if (isValid) {
                        viewModel.doLogin(request)
                    } else {
                        showSnackBar("Digite um cpf válido")
                    }
                } else {
                    viewModel.doLogin(request)
                }
            }
        } else {
            showSnackBar("Preencha todos os campos")
        }
    }

    private fun observeViewModel(view: View) {
        viewModel.account.observe(this, Observer { account ->
            account?.let {
                val action = LoginFragmentDirections.actionLoginFragmentToStatementFragment(account)
                Navigation.findNavController(view)
                    .navigate(action)
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    pDialog = ProgressDialog.show(
                        requireContext(),
                        "Carregando Informações", "Aguarde...", false, true
                    )
                } else {
                    pDialog.dismiss()
                }
            }
        })

        viewModel.loadError.observe(this, Observer { isError ->
            isError?.let {
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        "Falha ao realizar login",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
}
