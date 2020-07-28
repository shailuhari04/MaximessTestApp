package com.droidplusplus.maximesstask.data.dao

import androidx.lifecycle.LiveData;
import androidx.room.*
import com.droidplusplus.maximesstask.data.entity.DataItem

@Dao
interface DataItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dataItem: DataItem): Long

    @Update
    fun update(dataItem: DataItem)

    @Query("delete from data_item_table where id = :id")
    fun deleteById(id: Int)

    @Delete
    fun delete(dataItem: DataItem)

    @Query("select * from data_item_table")
    fun getAllDataItem(): LiveData<List<DataItem>>
}
