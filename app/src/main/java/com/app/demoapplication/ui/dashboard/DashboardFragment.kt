package com.app.demoapplication.ui.dashboard

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager.widget.ViewPager
import com.app.demoapplication.R
import com.app.demoapplication.databinding.FragmentDashboardBinding

import com.app.demoapplication.listener.TextChangeListener
import com.app.demoapplication.ui.MainActivity
import com.app.demoapplication.ui.base.BaseFragment
import com.app.demoapplication.ui.dashboard.adapter.DashboardRecyclerAdapter
import com.app.demoapplication.ui.dashboard.adapter.DashboardViewPagerAdapter
import com.app.demoapplication.utils.hideKeyboard
import com.app.demoapplication.viewmodels.DashboardViewModel


class DashboardFragment : BaseFragment() {

    private lateinit var mViewModel: DashboardViewModel
    private lateinit var mViewBinding: FragmentDashboardBinding

    private lateinit var mRecyclerAdapter: DashboardRecyclerAdapter
    private lateinit var mViewPagerAdapter: DashboardViewPagerAdapter

    override fun getLayoutId(): Int {
        return R.layout.fragment_dashboard
    }

    override fun onViewsInitialized(binding: ViewDataBinding, view: View) {
        mViewBinding = binding as FragmentDashboardBinding

        mViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        setListeners()
        setObservers()

        initCarouseViewPager()
        initCarouseRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.show()
    }


    private fun setListeners() {
        mViewBinding.pagerCarousel.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                mViewModel.setData(
                    mViewModel.getData(
                        position
                    )
                )
            }
        })

        mViewBinding.edtSearch.addTextChangedListener(TextChangeListener {
            mRecyclerAdapter.filter.filter(it)
        })
        mViewBinding.edtSearch.setOnClickListener {
            mViewBinding.rootMotionLayout.transitionToEnd()
            mViewBinding.rootMotionLayout.requestFocus()
        }
        mViewBinding.edtSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mViewBinding.edtSearch.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
        mViewBinding.edtSearch.setOnFocusChangeListener { v, hasFocus ->
            run {
                if (hasFocus) {
                    mViewBinding.rootMotionLayout.transitionToEnd()
                }
            }
        }


    }

    private fun setObservers() {
        mViewModel.carouselData.observe(viewLifecycleOwner, {
            mViewPagerAdapter.addItems(it)
        })

        mViewModel.selectedCarouselListData.observe(viewLifecycleOwner, {
            mRecyclerAdapter.setOriginalList(it)
        })
    }

    private fun initCarouseViewPager() {
        mViewPagerAdapter = DashboardViewPagerAdapter()
        mViewBinding.pagerCarousel.adapter = mViewPagerAdapter
        mViewBinding.tabDots.setupWithViewPager(mViewBinding.pagerCarousel, true)
    }

    private fun initCarouseRecyclerView() {
        mViewModel.setData(mViewModel.getData(0))
        mRecyclerAdapter = DashboardRecyclerAdapter {

        }
        mViewBinding.rvCarousel.run {
            addItemDecoration(DividerItemDecoration(context, 0))
            adapter = mRecyclerAdapter
        }

    }

}