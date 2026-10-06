package com.application.e_commerce.ui.payment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.application.e_commerce.R
import com.application.e_commerce.databinding.FragmentPaymentBinding
import java.util.Locale
import java.util.concurrent.TimeUnit

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private var countDownTimer: CountDownTimer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        startCountdown()

        binding.btnCheckStatus.setOnClickListener {
            showSuccessDialog()
        }
    }

    private fun startCountdown() {
        // 15 minutes in milliseconds
        val timeInMillis = 15 * 60 * 1000L
        
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val ms = String.format(
                    Locale.getDefault(),
                    "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % TimeUnit.MINUTES.toSeconds(1)
                )
                binding.tvCountdown.text = ms
            }

            override fun onFinish() {
                binding.tvCountdown.text = "00:00"
            }
        }.start()
    }

    private fun showSuccessDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Pembayaran Berhasil")
            .setMessage("Terima kasih, pembayaran Anda telah kami terima.")
            .setCancelable(false)
            .setPositiveButton("Lihat Status Pesanan") { _, _ ->
                findNavController().navigate(R.id.action_paymentFragment_to_trackingFragment)
            }
            .setNegativeButton("Tutup") { dialog, _ ->
                dialog.dismiss()
                findNavController().popBackStack(R.id.homeFragment, false)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer?.cancel()
        _binding = null
    }
}