package com.app.demoapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.demoapplication.pojo.CarouselData
import com.app.demoapplication.pojo.CarouselListData
import com.app.demoapplication.repo.DashboardRepository
import com.app.demoapplication.utils.ConfigConstant.Companion.CAROUSEL_COUNT
import com.app.demoapplication.utils.ConfigConstant.Companion.CAROUSEL_ITEM_COUNT

class DashboardViewModel : ViewModel() {

    private val repository = DashboardRepository()

    private val mCarouselData = MutableLiveData<List<CarouselData>>()
    val carouselData: LiveData<List<CarouselData>> = mCarouselData

    private val _selectedCarouselListData = MutableLiveData<List<CarouselListData>>()
    val selectedCarouselListData: LiveData<List<CarouselListData>> = _selectedCarouselListData

    init {
        mCarouselData.value =
            repository.getCarouselData(CAROUSEL_COUNT, CAROUSEL_ITEM_COUNT)
    }

    fun setData(carouselListData: List<CarouselListData>) {
        _selectedCarouselListData.postValue(carouselListData)
    }

    fun getData(position: Int): List<CarouselListData> {
        return mCarouselData.value?.get(position)?.data ?: listOf()
    }
}