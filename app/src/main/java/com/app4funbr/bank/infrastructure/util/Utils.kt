package com.app4funbr.bank.infrastructure.util

import com.nulabinc.zxcvbn.Strength
import com.nulabinc.zxcvbn.Zxcvbn

object Utils {

    /**
     * Este método recebe um pwd como parâmetro e devolve o score do mesmo
     * para que possa medir a força do pwd
     */
    fun getStregthPwd(word: String): String {
        val zxcvbn = Zxcvbn()
        val strength: Strength = zxcvbn.measure(word)
        return strength.score.toString()
    }
}