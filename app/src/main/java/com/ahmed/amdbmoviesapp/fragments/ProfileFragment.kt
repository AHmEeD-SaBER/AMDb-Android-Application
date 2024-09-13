package com.ahmed.amdbmoviesapp.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.activities.AuthenticationActivity

class ProfileFragment : Fragment() {

    private lateinit var logOutBtn : Button
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logOutBtn = view.findViewById(R.id.logoutBtn)
        logOutBtn.setOnClickListener {
            sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            editor.commit()
            val splashIntent = Intent(requireContext(), AuthenticationActivity::class.java)
            startActivity(splashIntent)
            requireActivity().finish()
        }
    }

}