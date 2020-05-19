package com.app4funbr.bank.infrastructure.network

import com.app4funbr.bank.interfaces.ServiceAPI
import com.app4funbr.bank.model.AccountRequest
import com.app4funbr.bank.model.AccountResponse
import com.app4funbr.bank.model.StatementResponse
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://bank-app-test.herokuapp.com/api/"
const val TIMEOUT_SECONDS = 60L
private val LOGGING_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY

class RestProvider {

    val logInterceptor = HttpLoggingInterceptor().apply {
        level = LOGGING_LEVEL
    }

    val clientBuilder = OkHttpClient()
        .newBuilder()
        .addInterceptor(logInterceptor)
        .retryOnConnectionFailure(true)

    val client = clientBuilder.build()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(ServiceAPI::class.java)

    fun fetchStatement(): Single<StatementResponse> {
        return api.getStatment()
    }

    fun doLogin(account: AccountRequest): Single<AccountResponse> {
        return api.doLogin(account)
    }
}