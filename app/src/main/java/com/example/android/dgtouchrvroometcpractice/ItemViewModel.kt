package com.example.android.dgtouchrvroometcpractice

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android.dgtouchrvroometcpractice.database.Item
import com.example.android.dgtouchrvroometcpractice.database.ItemDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TRY THIS...
// Viewmodel accesses db, gets LiveData from db--CHECK THIS
// Viewmodel updates the db as needed using functions and co-routines
// LiveData stays updated on its own
// Fragment observes the LiveData & feeds it to the adapter
// (the tricky part: keeping track of what things are live daya, mutable, arraylists, items...)

class ItemViewModel (
    val database: ItemDao,
    application: Application) : AndroidViewModel(application)  {

    private val items = database.getAllItemsByOrder()

    // create a new item, add it to the db
    suspend fun createItem(name: String) {
        if (name != "") {
            withContext(Dispatchers.IO) {
                val newItem = Item()
                newItem.name = name
                database.insert(newItem)
            }
        }
    }


    private suspend fun clear() {
        database.clear()
    }

    private suspend fun update(item: Item) {
        database.update(item)
    }

    private suspend fun insert(item: Item) {
        database.insert(item)
    }

    // just for testing early, make some parts when touch a temporary button
    // calls createPart() which interacts with the database
    fun makeTestItemsInDb() {
        viewModelScope.launch {
            createItem("1")
            createItem("2")
            createItem("3")
            createItem("4")
            createItem("5")
            createItem("6")
        }
    }
}