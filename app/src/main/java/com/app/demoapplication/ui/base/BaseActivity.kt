package com.app.demoapplication.ui.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.app.demoapplication.BuildConfig
import com.app.demoapplication.listener.BaseInterFace

abstract class BaseActivity : AppCompatActivity(),
    BaseInterFace, LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layoutIdRes = layout
        if (layoutIdRes != 0) {
            val binding = DataBindingUtil.setContentView(this, layoutIdRes) as ViewDataBinding
            initUI(binding)

        }

    }

}
