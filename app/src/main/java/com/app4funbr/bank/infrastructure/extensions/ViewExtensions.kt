package com.app4funbr.bank.infrastructure.extensions

import android.widget.TextView
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

val locale = Locale("pt", "BR")

fun TextView.formatToCurreny(value: Double) {
    val newValue = NumberFormat.getCurrencyInstance(locale).format(value)
    this.text = newValue.toString()
}
