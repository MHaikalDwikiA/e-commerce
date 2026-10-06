package com.application.e_commerce.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.application.e_commerce.R
import com.application.e_commerce.databinding.FragmentCartBinding
import java.text.NumberFormat
import java.util.Locale

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        setupRecyclerView()
        observeViewModel()

        viewModel.loadCart()

        binding.btnCheckout.setOnClickListener {
            if (viewModel.cartItems.value.isNullOrEmpty()) {
                Toast.makeText(context, "Keranjang kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val total = viewModel.totalPrice.value ?: 0.0
            val bundle = Bundle().apply {
                putDouble("totalPrice", total)
            }
            findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment, bundle)
        }
    }

    private fun setupRecyclerView() {
        adapter = CartAdapter(
            onIncrease = { item -> viewModel.updateQuantity(item, item.quantity + 1) },
            onDecrease = { item -> viewModel.updateQuantity(item, item.quantity - 1) },
            onDelete = { item -> viewModel.deleteItem(item) }
        )
        binding.rvCart.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.tvEmptyCart.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.totalPrice.observe(viewLifecycleOwner) { total ->
            val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
            binding.tvTotalPrice.text = format.format(total)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}