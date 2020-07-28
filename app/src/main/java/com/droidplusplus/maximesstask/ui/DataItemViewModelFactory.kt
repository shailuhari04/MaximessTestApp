package com.droidplusplus.maximesstask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.droidplusplus.maximesstask.repository.DataItemRepository

class DataItemViewModelFactory (private val dataItemRepository: DataItemRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataItemViewModel::class.java)) {
            return DataItemViewModel(dataItemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}