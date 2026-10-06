package com.application.e_commerce.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.application.e_commerce.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import android.graphics.Paint
import java.text.NumberFormat
import java.util.Locale

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getString("productId") ?: return
        
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        observeViewModel()
        viewModel.loadProductDetail(productId)
        
        binding.llReviews.setOnClickListener {
            findNavController().navigate(com.application.e_commerce.R.id.action_detailFragment_to_reviewsFragment)
        }
        
        binding.btnAddToCartOutline.setOnClickListener {
            viewModel.product.value?.let {
                viewModel.addToCart(it)
            }
        }
        
        binding.btnBuyNow.setOnClickListener {
            // direct to checkout or add to cart and go to cart
            viewModel.product.value?.let {
                viewModel.addToCart(it)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.product.observe(viewLifecycleOwner) { product ->
            binding.tvProductName.text = product.name
            
            val priceValue = product.price.toDoubleOrNull() ?: 0.0
            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            binding.tvProductPrice.text = format.format(priceValue)
            
            binding.tvOldPrice.paintFlags = binding.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            
            binding.tvDescription.text = product.description

            Glide.with(this)
                .load(product.imgUrl)
                .centerCrop()
                .into(binding.ivProduct)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.addToCartSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(context, "Berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}