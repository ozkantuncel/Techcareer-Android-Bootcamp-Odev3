package com.example.odev3.ui

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.odev3.R
import com.example.odev3.data.model.Food
import com.example.odev3.databinding.FragmentMainScreenBinding
import com.example.odev3.ui.adapter.HorizontalFoodAdapter
import com.example.odev3.ui.adapter.VerticalFoodAdapter
import com.google.android.material.chip.Chip


class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private val categories = listOf("Hepsi","Kebab","Çorba","Köfte","Pilav","Makarna")
    private val favorites = mutableListOf<Food>()
    private var selectedCategoryIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        for(i in categories.indices){
            val chip = layoutInflater.inflate(R.layout.chip_main, null, false) as Chip
            chip.text = categories[i]
            chip.isClickable = true
            chip.isChecked = i == selectedCategoryIndex
            chip.setOnClickListener{
                selectChip(i)
            }
            binding.chipGroup.addView(chip)
        }


        val recyclerViewRow: RecyclerView = binding.recyclerViewRec
        recyclerViewRow.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val recyclerViewHor:RecyclerView = binding.recyclerViewHor
        recyclerViewHor.layoutManager = LinearLayoutManager(requireContext())

        val items = listOf(
            Food(id = 0, name = "Sis Kebab", imageResource = R.drawable.kebab_pic, kind = "Kebab", rat = "4.6", price = "200tl"),
            Food(id = 0, name = "Tavuk Çorbasi", imageResource = R.drawable.soup_pic, kind = "Çorba", rat = "4.1", price = "50tl"),
            Food(id = 0, name = "Döner Kebabı", imageResource = R.drawable.lamb_pic, kind = "Kebab", rat = "4.3", price = "150tl"),
            Food(id = 0, name = "İçli Köfre", imageResource = R.drawable.kofte_pic, kind = "Köfte", rat = "4.6", price = "100tl")
        )

        val adapterRow = HorizontalFoodAdapter(items)
        recyclerViewRow.adapter = adapterRow

        val adapterHor = VerticalFoodAdapter(items)
        recyclerViewHor.adapter = adapterHor

        adapterHor.onItemClickListener = {view, position ->

            if (favorites.contains(items[position])){
                view.setColorFilter(ContextCompat.getColor(requireContext(), R.color.heartColorOff), PorterDuff.Mode.SRC_IN)
                favorites.remove(items[position])
            }else{
                view.setColorFilter(ContextCompat.getColor(requireContext(), R.color.heartColorOn), PorterDuff.Mode.SRC_IN)
                favorites.add(items[position])
            }
        }




    }

    private fun selectChip(index: Int) {

        val previousChip = binding.chipGroup.getChildAt(selectedCategoryIndex) as Chip
        previousChip.isChecked = false
        previousChip.setChipBackgroundColorResource(R.color.chipGray)

        val selectedChip = binding.chipGroup.getChildAt(index) as Chip
        selectedChip.isChecked = true
        selectedChip.setChipBackgroundColorResource(R.color.chipTextPress)

        selectedCategoryIndex = index

    }


}