package com.example.todowithroomdb.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todowithroomdb.data.model.ListData
import com.example.todowithroomdb.databinding.ItemListViewBinding

/**
 * Created by Himanshu Verma on 08/06/24.
 **/
class Adapter(var listToBeDisplayed: ArrayList<ListData>, var callBack: callBack) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private lateinit var binding: ItemListViewBinding

    inner class MyViewHolder(binding: ItemListViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = ItemListViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listToBeDisplayed.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(listToBeDisplayed[position]) {
            binding.listText.text = this.note
        }
        binding.root.setOnClickListener {
            callBack.onItemClicked(position)
        }
    }

    fun addData(list: ArrayList<ListData>) {
        listToBeDisplayed = list
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        listToBeDisplayed.removeAt(position)
        notifyItemRemoved(position)
    }


}

interface callBack {
    fun onItemClicked(position: Int)
}