package com.app4funbr.bank.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app4funbr.bank.R
import com.app4funbr.bank.model.Statement

class StatementAdapter(val list: ArrayList<Statement>) :
    RecyclerView.Adapter<StatementAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_statement, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val statment = list[position]
        holder.title.text = statment.title
        holder.desc.text = statment.desc
        holder.date.text = statment.date
        holder.value.text = "R$ ${statment.value}"
    }

    fun updateStatementList(newList: List<Statement>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.statement_type)
        val desc = view.findViewById<TextView>(R.id.text_description)
        val date = view.findViewById<TextView>(R.id.text_date)
        val value = view.findViewById<TextView>(R.id.text_amount)
    }
}