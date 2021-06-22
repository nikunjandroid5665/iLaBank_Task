package com.app.demoapplication.ui.dashboard

import android.widget.Filter
import com.app.demoapplication.pojo.CarouselListData

class FilterData<T>(
    private val carouselData: List<CarouselListData>,
    val onFilter: (List<T>) -> Unit
) : Filter() {
    override fun performFiltering(ch: CharSequence?): FilterResults {
        var filteredList = mutableListOf<CarouselListData>()

        if (ch.isNullOrEmpty()) {
            filteredList = carouselData.toMutableList()
        } else {
            for (i in carouselData) {
                if (i.carouselName.contains(ch, ignoreCase = true))
                    filteredList.add(i)
            }
        }
        return FilterResults().apply { values = filteredList }
    }

    override fun publishResults(ch: CharSequence?, p1: FilterResults?) {
        try {
            onFilter(p1?.values as List<T>)
        } catch (e: Exception) {
            onFilter(listOf())
        }
    }

}