package com.example.android.dgtouchrvroometcpractice

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.dgtouchrvroometcpractice.database.Item

import java.util.*

// just for now testing
//var lista = mutableListOf ("1", "2", "3", "4", "5", "6")
//var listB =

class RVAdapter(context: Context, val lista: MutableList<Item>): RecyclerView.Adapter<MyViewHolder>(), TouchHelperInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.rv_item,parent,false) as TextView
        return MyViewHolder(view)
        }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = lista[position]
        holder.textView.text = item.name
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    // TouchHelperInterface methods
    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(lista, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(lista, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        Log.i("tag", "moved item using onItemMove in PartAdapter")

    }

    override fun onItemDismiss(position: Int) {
        lista.removeAt(position)
        notifyItemRemoved(position)
        Log.i("tag", "removed item using onDismiss in PartAdapter")
    }

}



