package com.droidplusplus.maximesstask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.droidplusplus.maximesstask.data.dao.DataItemDao
import com.droidplusplus.maximesstask.data.entity.DataItem
import kotlinx.coroutines.CoroutineScope

@Database(entities = [DataItem::class], version = 1)
abstract class DataItemRoomDatabase : RoomDatabase() {

    abstract fun dataItemDao(): DataItemDao

    companion object {
        private var INSTANCE: DataItemRoomDatabase? = null

        fun getDBInstance(context: Context): DataItemRoomDatabase {
            if (INSTANCE == null) {
                synchronized(DataItemRoomDatabase::class) {
                    INSTANCE = buildRoomDb(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDb(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                DataItemRoomDatabase::class.java,
                "data_item_database.db"
            ).build()

    }

}
