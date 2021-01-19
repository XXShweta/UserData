package com.project.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager.*
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity<T : ViewDataBinding> : DaggerAppCompatActivity(), BaseFragment.Callback {


    var viewDataBinding: T? = null

    abstract fun getBindingVariable(): Int

    abstract fun getLayoutId(): Int


    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()

    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }



    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding!!.lifecycleOwner=this
        viewDataBinding!!.executePendingBindings()
    }

    open fun getActivityDataBinding(): T {
        return viewDataBinding!!
    }

}