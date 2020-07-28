package com.droidplusplus.maximesstask.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.droidplusplus.maximesstask.data.entity.DataItem
import com.droidplusplus.maximesstask.repository.DataItemRepository

class DataItemViewModel(private val dataItemRepository: DataItemRepository) : ViewModel() {

    fun insert(dataItem: DataItem) {
        return dataItemRepository.insert(dataItem)
    }

    fun delete(dataItem: DataItem) {
        dataItemRepository.delete(dataItem)
    }

    fun update(dataItem: DataItem) {
        dataItemRepository.update(dataItem)
    }

    fun getAllNotes(): LiveData<List<DataItem>> {
        return dataItemRepository.getAllDataItem()
    }

}