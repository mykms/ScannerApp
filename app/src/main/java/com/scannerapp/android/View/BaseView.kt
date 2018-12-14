package com.scannerapp.android.View

interface BaseView {
    fun onError(message: String)
    fun onProgress(isProgress: Boolean)
}