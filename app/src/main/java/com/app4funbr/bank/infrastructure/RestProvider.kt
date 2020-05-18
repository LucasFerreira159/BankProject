package com.app4funbr.bank.infrastructure

import com.app4funbr.bank.interfaces.ServiceAPI
import com.app4funbr.bank.model.StatementResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"

class RestProvider {

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ServiceAPI::class.java)

    fun fetchStatement(): Single<StatementResponse> {
        return api.getStatment()
    }
}