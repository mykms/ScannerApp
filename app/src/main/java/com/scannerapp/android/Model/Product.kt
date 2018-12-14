package com.scannerapp.android.Model

class Product() {
    constructor(code: String) : this() {
        this.text = code
    }

    private var text: String = ""
    private val region: Region = Region("0c5b2444-70a0-4932-980c-b4dc0d3f02b5", null, null)
}