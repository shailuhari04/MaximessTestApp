package com.droidplusplus.maximesstask.repository

import androidx.lifecycle.LiveData
import com.droidplusplus.maximesstask.data.dao.DataItemDao
import com.droidplusplus.maximesstask.data.entity.DataItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataItemRepositoryImpl(private val dataItemDao: DataItemDao) : DataItemRepository {

    override fun insert(dataItem: DataItem) {
        CoroutineScope(Dispatchers.IO).launch {
            dataItemDao.insert(dataItem)
        }
    }

    override fun delete(dataItem: DataItem) {
        CoroutineScope(Dispatchers.IO).launch {
            dataItemDao.delete(dataItem)
        }
    }

    override fun deleteById(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dataItemDao.deleteById(id)
        }
    }

    override fun update(dataItem: DataItem) {
        CoroutineScope(Dispatchers.IO).launch {
            dataItemDao.update(dataItem)
        }
    }

    override fun getAllDataItem(): LiveData<List<DataItem>> {
        return dataItemDao.getAllDataItem()
    }
}