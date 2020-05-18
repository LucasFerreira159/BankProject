package com.app4funbr.bank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app4funbr.bank.infrastructure.RestProvider
import com.app4funbr.bank.model.Statement
import com.app4funbr.bank.model.StatementResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class StatementViewModel: ViewModel() {

    private val restProvider = RestProvider()
    private val disposable = CompositeDisposable()

    val statment = MutableLiveData<List<Statement>>()
    val loading = MutableLiveData<Boolean>()
    val loadError = MutableLiveData<Boolean>()

    fun fetchStatement() {
        loading.value = true
        disposable.add(
            restProvider.fetchStatement()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<StatementResponse>() {
                    override fun onSuccess(t: StatementResponse) {
                        loading.value = false
                        statment.value = t.statementList
                        loadError.value = false
                    }

                    override fun onError(e: Throwable) {
                        loadError.value = true
                        loading.value = false
                    }
                })
        )
    }
}