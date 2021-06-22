package com.app.demoapplication.pojo

import androidx.annotation.DrawableRes

data class CarouselData(
        @DrawableRes val carouselImage: Int,
        val data: List<CarouselListData>)