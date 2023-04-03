package com.example.odev3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.odev3.data.model.Food
import com.example.odev3.databinding.RowItemsBinding

class HorizontalFoodAdapter
    (private val items: List<Food>) : RecyclerView.Adapter<HorizontalFoodAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: RowItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Food){
            binding.imageFood.setImageResource(item.imageResource)
            binding.textFood.text = item.name
            binding.foodKind.text = item.kind
            binding.foodStatus.text = item.rat
            binding.foodPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
     }


}