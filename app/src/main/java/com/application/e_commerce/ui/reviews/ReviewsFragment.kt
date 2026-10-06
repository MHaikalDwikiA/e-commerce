package com.application.e_commerce.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.application.e_commerce.databinding.FragmentReviewsBinding
import com.application.e_commerce.databinding.ItemReviewBinding

class ReviewsFragment : Fragment() {

    private var _binding: FragmentReviewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvReviews.adapter = ReviewAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Dummy Adapter to show items
    inner class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

        inner class ReviewViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
            val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ReviewViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
            // Just displaying static design from XML for dummy data
        }

        override fun getItemCount(): Int = 5 // Show 5 dummy reviews
    }
}