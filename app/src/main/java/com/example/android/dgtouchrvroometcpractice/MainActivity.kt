package com.example.android.dgtouchrvroometcpractice

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android.dgtouchrvroometcpractice.database.Item
import com.example.android.dgtouchrvroometcpractice.database.ItemDatabase

class MainActivity : AppCompatActivity() {

    var viewModel: ItemViewModel? = null
    var recyclerView: RecyclerView? = null
    var adapter: RVAdapter? = null
    lateinit var rv: RecyclerView
    lateinit var allItems: MutableList<Item>
    lateinit var database: ItemDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val application = this.application

//        val database = ItemDatabase.getInstance(this.applicationContext)
         database = Room.databaseBuilder(
            this,ItemDatabase::class.java, "item_database"
        )
            .allowMainThreadQueries()
            .build()

        allItems = database.itemDatabaseDao.getAllItems()
        Log.i(TAG, "allItems size ${allItems.size}" )

        setupRv()

        val buttonAdd: Button = findViewById (R.id.button_add)
        buttonAdd.setOnClickListener(View.OnClickListener(){
            makeItems()
            Log.i(TAG, "made items")
        })

        val buttonClear: Button = findViewById (R.id.button_clear)
        buttonClear.setOnClickListener(View.OnClickListener(){
            clearItems()
            Log.i(TAG, "cleared items")
        })

//        //Users live data
//        var userListUpdateObserver: Observer<ArrayList<Item>?> =
//            Observer<ArrayList<Item>?> { itemArrayList ->
//                adapter = RVAdapter(this@MainActivity, allItems )
//                recyclerView!!.layoutManager = LinearLayoutManager(this@MainActivity)
//                recyclerView!!.adapter = adapter
//            }
    }

    fun setupRv() {
        val rv: RecyclerView = findViewById(R.id.rv_view)
        rv.apply {
            val adapter = RVAdapter(context, allItems)
            rv.layoutManager = LinearLayoutManager(this@MainActivity)
            rv.adapter = adapter

        val callback: ItemTouchHelper.Callback = TouchHelperCallback(adapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rv)

        }
    }

    fun makeItems(){
        Log.i(TAG, "Called makeItems")
        database.itemDatabaseDao.insert(Item(name ="1", order = 1))
        database.itemDatabaseDao.insert(Item(name ="2", order = 2))
        database.itemDatabaseDao.insert(Item(name ="3", order = 3))
        allItems = database.itemDatabaseDao.getAllItems()
        Log.i(TAG, "items count from make is ${allItems.size}")
        setupRv()
    }

    fun clearItems(){
        Log.i(TAG, "Called makeItems")
        // adding to db but not yet updating b/c no livedata
        database.itemDatabaseDao.clear()
        allItems = database.itemDatabaseDao.getAllItems()
        Log.i(TAG, "items count from clear is ${allItems.size}")
        setupRv()
    }

}