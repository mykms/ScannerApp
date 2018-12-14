package com.scannerapp.android.View

import com.scannerapp.android.Model.ProductResult

interface SearchView : BaseView {
    fun onResult(productResult: ProductResult?)
}