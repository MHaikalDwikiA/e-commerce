package com.application.e_commerce.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.e_commerce.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Dummy actions for buttons
        val menuLogout = view.findViewById<View>(R.id.menuLogout)
        menuLogout?.setOnClickListener {
            Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show()
        }
    }
}