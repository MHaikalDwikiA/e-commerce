package com.application.e_commerce.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.e_commerce.R

class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvCategories = view.findViewById<RecyclerView>(R.id.rvCategories)
        val dummyCategories = listOf(
            "Elektronik", "Pakaian", "Makanan", "Minuman", 
            "Kesehatan", "Otomotif", "Mainan", "Olahraga",
            "Buku", "Dapur", "Kecantikan", "Lainnya"
        )
        rvCategories.layoutManager = GridLayoutManager(context, 4)
        rvCategories.adapter = CategoryAdapter(dummyCategories)
    }
}