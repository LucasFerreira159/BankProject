package com.app4funbr.bank.interfaces

import com.app4funbr.bank.model.StatementResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceAPI {

    @GET("statements/1")
    fun getStatment(): Single<StatementResponse>
}