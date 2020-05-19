package com.app4funbr.bank.infrastructure.extensions

import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.util.*


val locale = Locale("pt", "BR")

fun TextView.formatToCurreny(value: Double) {
    val newValue = NumberFormat.getCurrencyInstance(locale).format(value)
    this.text = newValue.toString()
}

fun Fragment.showSnackBar(title: String) {
    val snackbar: Snackbar = Snackbar.make(this.requireView(), title, Snackbar.LENGTH_SHORT)
    snackbar.show()
    val view: View = snackbar.view
    val txtv = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    txtv.gravity = Gravity.CENTER_HORIZONTAL
}