package com.scannerapp.android.UI.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.scannerapp.android.R
import com.scannerapp.android.UI.Activity.ScannerActivity

class ScannerFragment : BaseFragment() {
    private var btScan: Button? = null

    override fun getArgs(args: Bundle?) {
        //
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_scaner
    }

    override fun initComponents(view: View) {
        btScan = view.findViewById(R.id.bt_scan_start)
        btScan?.setOnClickListener {
            val scanIntentIntent = Intent(activity, ScannerActivity::class.java)
            startActivityForResult(scanIntentIntent, 125)
        }
    }
}
