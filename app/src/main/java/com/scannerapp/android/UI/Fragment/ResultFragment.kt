package com.scannerapp.android.UI.Fragment

import android.os.Bundle
import android.view.View
import com.scannerapp.android.Constants
import com.scannerapp.android.R

class ResultFragment : BaseFragment() {
    private var barCode: String = ""
    override fun getArgs(args: Bundle?) {
        barCode = args?.getString(Constants.EXTRA_CODE, "").toString()
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_result
    }

    override fun initComponents(view: View) {
        if (!barCode.isNullOrEmpty()) {
            //запрос в сеть
        }
    }

}
