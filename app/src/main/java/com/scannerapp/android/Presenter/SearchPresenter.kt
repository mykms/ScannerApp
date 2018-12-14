package com.scannerapp.android.Presenter

import com.scannerapp.android.Network.ApiMethods
import com.scannerapp.android.Model.Product
import com.scannerapp.android.Model.ProductResult
import com.scannerapp.android.View.SearchView

class SearchPresenter(private val view: SearchView, private val apiMethods: ApiMethods) : BasePresenter() {
    fun searchProduct(product: Product) {
        val req = apiMethods.searchProduct(product)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val prodRes: ProductResult? = response.body()
                    prodRes?.result = 0
                    view.onResult(prodRes)
//                    if (!prodRes?.error_message.isNullOrEmpty()) {
//                        view.onError(prodRes?.error_message!!)
//                    }
                } else {
                    view.onError("Запрос прошел, но есть ошибка ${response.code()}")
                }
                view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onError("Сетевая ошибка ${onFailure?.message}")
                view.onProgress(false)
            }
        }
    }
}