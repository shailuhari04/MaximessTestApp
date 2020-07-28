package com.droidplusplus.maximesstask.repository

import androidx.lifecycle.LiveData
import com.droidplusplus.maximesstask.data.entity.DataItem

interface DataItemRepository {

    fun insert(dataItem: DataItem)

    fun delete(dataItem: DataItem)

    fun deleteById(id: Int)

    fun update(dataItem: DataItem)

    fun getAllDataItem(): LiveData<List<DataItem>>
}