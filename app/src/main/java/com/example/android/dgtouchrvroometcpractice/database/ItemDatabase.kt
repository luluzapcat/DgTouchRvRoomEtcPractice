package com.example.android.dgtouchrvroometcpractice.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract val itemDatabaseDao: ItemDao
}

//    // call `ItemDatabase.getInstance(context)` to instantiate a new ItemDatabase
//    companion object {
//
//        @Volatile
//        private var INSTANCE: ItemDatabase? = null
//
//        fun getInstance(context: Context): ItemDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        ItemDatabase::class.java,
//                        "item_database"
//                    )
//
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
//}
