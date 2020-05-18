package com.app4funbr.bank.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app4funbr.bank.R
import com.app4funbr.bank.view.adapter.StatementAdapter
import com.app4funbr.bank.viewmodel.StatementViewModel
import kotlinx.android.synthetic.main.content_statment.*

class StatementFragment : Fragment() {

    private lateinit var viewModel: StatementViewModel
    private var adapterStatement = StatementAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StatementViewModel::class.java)
        viewModel.fetchStatement()

        recycler_view?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterStatement
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.statment.observe(this, Observer { statement ->
            statement?.let {
                text_label_recent?.visibility = View.VISIBLE
                recycler_view?.visibility = View.VISIBLE
                adapterStatement.updateStatementList(statement)
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progress_bar?.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    text_recycler_error?.visibility = View.GONE
                    recycler_view?.visibility = View.GONE
                }
            }
        })

        viewModel.loadError.observe(this, Observer { isError ->
            isError?.let {
                text_recycler_error?.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}
