package com.example.odev3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.odev3.data.model.Food
import com.example.odev3.databinding.HorItemsBinding

class VerticalFoodAdapter(
    private val items: List<Food>) : RecyclerView.Adapter<VerticalFoodAdapter.ViewHolder>()
{

    var onItemClickListener: ((ImageView, Int) -> Unit)? = null

    inner class ViewHolder( private val binding: HorItemsBinding): RecyclerView.ViewHolder(binding.root){
        val heart = binding.imageHeart
         fun bind(item: Food){
            binding.imageFood.setImageResource(item.imageResource)
            binding.textFood.text = item.name
            binding.foodKind.text = item.kind
            binding.foodStatus.text = item.rat
            binding.foodPrice.text = item.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalFoodAdapter.ViewHolder {
        val binding = HorItemsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VerticalFoodAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.heart.setOnClickListener{
            onItemClickListener?.invoke(holder.heart,position)
        }

    }


}