package com.example.android.dgtouchrvroometcpractice

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class TouchHelperCallback (intrfc: TouchHelperInterface): ItemTouchHelper.Callback() {

    private val intrfc: TouchHelperInterface = intrfc

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
        val swipeFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        intrfc.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition())
        Log.i("tag", "onItemMoved called from onMove in touchhelpercallback")

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // without below, it removes item but doesn't close the gap--not RV oriented w/o it i guess
        intrfc.onItemDismiss(viewHolder.getAdapterPosition())
        Log.i("tag", "onItemDismiss called from onSwiped in touchhelpercallback")
    }


    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
}