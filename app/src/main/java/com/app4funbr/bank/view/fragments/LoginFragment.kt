package com.app4funbr.bank.view.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.app4funbr.bank.R
import com.app4funbr.bank.model.AccountRequest
import com.app4funbr.bank.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.content_statment.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var pDialog: ProgressDialog

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
            /**/
            doLogin()
        }
    }

    private fun doLogin() {
        val user = edit_user.text.toString()
        val pwd = edit_pwd.text.toString()

        if (!user.isNullOrEmpty() && !pwd.isNullOrEmpty()) {
            val request = AccountRequest(user, pwd)
            viewModel.doLogin(request)
        } else {
            Toast.makeText(
                requireContext(),
                "Preencha todos os campos",
                Toast.LENGTH_SHORT
            ).show()
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
