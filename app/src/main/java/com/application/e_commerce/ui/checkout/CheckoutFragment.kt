package com.application.e_commerce.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.e_commerce.R
import com.application.e_commerce.databinding.FragmentCheckoutBinding
import java.text.NumberFormat
import java.util.Locale

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private var subTotal: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subTotal = arguments?.getDouble("totalPrice") ?: 0.0

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        updateGrandTotal(15000.0) // default reguler

        binding.rgShipping.setOnCheckedChangeListener { _, checkedId ->
            val shippingCost = if (checkedId == R.id.rbReguler) 15000.0 else 25000.0
            updateGrandTotal(shippingCost)
        }

        binding.btnPay.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_paymentFragment)
        }
    }

    private fun updateGrandTotal(shippingCost: Double) {
        val grandTotal = subTotal + shippingCost
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        binding.tvGrandTotal.text = format.format(grandTotal)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}