package com.scannerapp.android.Presenter

import com.scannerapp.android.Network.ResponseCallBack
import retrofit2.Call

open class BasePresenter {
    protected fun<T> Call<T>.enqueue(callback: ResponseCallBack<T>.() -> Unit) {
        val callBackKt = ResponseCallBack<T>()
        callback.invoke(callBackKt)
        this.enqueue(callBackKt)
    }
}