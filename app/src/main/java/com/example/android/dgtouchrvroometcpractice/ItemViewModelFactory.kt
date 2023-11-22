package com.example.android.dgtouchrvroometcpractice

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.dgtouchrvroometcpractice.database.ItemDao

// to create a viewmodel with input parameters
class ItemViewModelFactory(
    private val dataSource: ItemDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            return com.example.android.dgtouchrvroometcpractice.ItemViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ItemViewModel class")
    }
}