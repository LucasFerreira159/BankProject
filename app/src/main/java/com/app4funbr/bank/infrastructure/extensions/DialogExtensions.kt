package com.app4funbr.bank.infrastructure.extensions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.app4funbr.bank.R

fun Fragment.showPwdStrength(context: Context, score: String) {
    val contentView = LayoutInflater.from(context)
        .inflate(R.layout.dialog_score, null, false)

    val dialog = AlertDialog.Builder(context)
    dialog.setView(contentView)
    dialog.setTitle("Seu score é $score")
    dialog.setPositiveButton("Tentar Novamente") { dialog, _ ->
        dialog.dismiss()
    }
    dialog.show()
}