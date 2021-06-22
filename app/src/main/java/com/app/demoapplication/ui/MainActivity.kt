package com.app.demoapplication.ui

import androidx.databinding.ViewDataBinding
import com.app.demoapplication.R
import com.app.demoapplication.ui.base.BaseActivity

class MainActivity : BaseActivity() {


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initUI(binding: ViewDataBinding?) {

    }


}