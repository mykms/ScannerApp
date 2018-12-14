package com.scannerapp.android.UI.Fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.scannerapp.android.UI.Callback.HostActivityListener

abstract class BaseFragment : Fragment() {
    private var activityListener: HostActivityListener? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is HostActivityListener) {
            activityListener = activity
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HostActivityListener) {
            activityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgs(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(setResourceLayout(), container, false)
        initComponents(view)
        return view
    }

    fun onMessage(message: String) {
        activityListener?.onMessage(message)
    }

    fun openFragment(fragmentNavId: Int, args: Bundle) {
        activityListener?.onNavigateTo(fragmentNavId, args)
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }

    abstract fun getArgs(args: Bundle?)
    /**
     * Устанавливает разметку
     */
    abstract fun setResourceLayout(): Int

    /**
     * В это методе можно инициализировать свои компоненты
     */
    abstract fun initComponents(view: View)
}