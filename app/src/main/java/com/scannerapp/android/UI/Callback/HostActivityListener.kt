package com.scannerapp.android.UI.Callback

import android.os.Bundle

interface HostActivityListener {
    fun onMessage(message: String)
    fun onNavigateTo(fragmentNavId: Int, args: Bundle)
}