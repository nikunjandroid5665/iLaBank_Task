package com.app.demoapplication.listener

import androidx.recyclerview.widget.DiffUtil
import com.app.demoapplication.pojo.CarouselListData

class CarouselListDataItemCallback : DiffUtil.ItemCallback<CarouselListData>() {
    override fun areItemsTheSame(
        oldItem: CarouselListData,
        newItem: CarouselListData
    ): Boolean {
        return oldItem.carouselId == newItem.carouselId
    }

    override fun areContentsTheSame(
        oldItem: CarouselListData,
        newItem: CarouselListData
    ): Boolean {
        return oldItem == newItem
    }
}