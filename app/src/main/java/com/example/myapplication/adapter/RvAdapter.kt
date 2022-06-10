package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemRvBinding
import com.example.myapplication.models.Properties

class RvAdapter(var list: ArrayList<Properties>,var onCLick: OnCLick) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(properties: Properties, position: Int) {
            when (properties.gender) {
                "M" -> {
                    itemRv.view.setBackgroundResource(R.color.blueLine)
                }
                "F" -> {
                    itemRv.view.setBackgroundResource(R.color.redLine)
                }

            }
            itemRv.tvNames.text = properties.name
            itemRv.root.setOnClickListener {
                onCLick.click(list[position],position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent?.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}interface OnCLick{
    fun click(properties: Properties,position: Int)
}