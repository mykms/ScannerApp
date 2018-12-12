package com.scannerapp.android.UI.Callback

interface HostActivityListener {
    fun onMessage(message: String)
    fun onNavigateTo(fragmentNavId: Int)
}