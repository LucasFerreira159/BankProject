package com.app4funbr.bank.interfaces

import com.app4funbr.bank.model.AccountRequest
import com.app4funbr.bank.model.AccountResponse
import com.app4funbr.bank.model.StatementResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {

    @POST("login")
    fun doLogin(@Body accountRequest: AccountRequest): Single<AccountResponse>

    @GET("statements/1")
    fun getStatment(): Single<StatementResponse>
}