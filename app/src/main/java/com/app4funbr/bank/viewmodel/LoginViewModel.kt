package com.app4funbr.bank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app4funbr.bank.infrastructure.network.RestProvider
import com.app4funbr.bank.model.Account
import com.app4funbr.bank.model.AccountRequest
import com.app4funbr.bank.model.AccountResponse
import com.app4funbr.bank.model.StatementResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class LoginViewModel: ViewModel() {

    private val restProvider = RestProvider()
    private val disposable = CompositeDisposable()

    val account = MutableLiveData<Account>()
    val loading = MutableLiveData<Boolean>()
    val loadError = MutableLiveData<Boolean>()

    fun doLogin(accountRequest: AccountRequest) {
        loading.value = true
        disposable.add(
            restProvider.doLogin(accountRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AccountResponse>() {
                    override fun onSuccess(t: AccountResponse) {
                        loadError.value = false
                        loading.value = false
                        account.value = t.userAccount
                    }

                    override fun onError(e: Throwable) {
                        loadError.value = true
                        loading.value = false
                    }
                })
        )
    }
}