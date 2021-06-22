package com.app.demoapplication.repo

import com.app.demoapplication.R
import com.app.demoapplication.pojo.CarouselData
import com.app.demoapplication.pojo.CarouselListData

class DashboardRepository {

    private val carouselData = mutableListOf<CarouselData>()

    fun getCarouselData(
        carouselCount: Int = 1,
        carouselItemCount: Int = 1
    ): List<CarouselData> {
        carouselData.clear()
        for (i in 1..carouselCount) {
            val carouselTempData = mutableListOf<CarouselListData>()
            for (j in 1..carouselItemCount) {
                carouselTempData.add(
                    CarouselListData(
                        carouselId = j,
                        carouselName = "Label ${i.times(10).plus(j)}"
                    )
                )
            }
            carouselData.add(
                CarouselData(
                    carouselImage = R.drawable.three,
                    data = carouselTempData
                )
            )
        }

        return carouselData
    }
}