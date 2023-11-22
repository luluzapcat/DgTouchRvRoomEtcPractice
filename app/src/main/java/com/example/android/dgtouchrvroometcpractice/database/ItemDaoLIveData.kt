package com.example.android.dgtouchrvroometcpractice.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDaoLIveData {

    @Insert
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)

    @Query("SELECT * from item_table WHERE id = :key")
    suspend fun get(key: Long): Item?

    @Query("SELECT * from item_table WHERE name = :name")
    suspend fun getItemByName(name: String): Item?

    @Query("SELECT * from item_table WHERE id = :key")
    fun getItemWithIdKey(key: Long): LiveData<Item>

    @Query("DELETE FROM item_table")
    suspend fun clear()

    @Query("SELECT * FROM item_table ORDER BY id DESC")
    fun getAllItems(): LiveData<List<Item>>

    @Query("SELECT MAX(`order`) FROM item_table")
    fun getMaxOrder(): Int

    // try out get count of parts db entries
    @Query("SELECT COUNT('partId') FROM item_table")
    open fun getItemCount(): Int

    // try out multiple updates
    @Query("UPDATE item_table SET 'order' = :new_order WHERE id IN (:ids)")
    fun updateItemOrder(ids:List<Int>, new_order:Int)

    // trying out if helps w/ remember new positions
    @Query ("SELECT * from item_table ORDER BY `order` DESC" )
    fun getAllItemsByOrder(): LiveData<List<Item>>

}

