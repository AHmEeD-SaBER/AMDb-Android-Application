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
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.activities.HomeActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var email : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var loginBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private lateinit var signUpTxt : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        signUpTxt = view.findViewById(R.id.navToSignup)
        email = view.findViewById(R.id.email)
        password = view.findViewById(R.id.password)
        loginBtn = view.findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(emailText, passwordText)
        }

        signUpTxt.setOnClickListener {
            findNavController().navigate(R.id.signupFragment)
        }
    }
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                    // Retrieve user details from Firestore
                    db.collection("users").document(userId).get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                val userEmail = document.getString("email") ?: "No email"
                                val userPhone = document.getString("phone") ?: "No phone"
                                val userName = document.getString("name") ?: "User"

                                // Save user details to SharedPreferences
                                val editor = sharedPreferences.edit()
                                editor.putString("userId", userId)
                                editor.putString("userEmail", userEmail)
                                editor.putString("userPhone", userPhone)
                                editor.putString("userName", userName)
                                editor.apply()

                                Toast.makeText(requireContext(), "Login successful! Welcome $userName", Toast.LENGTH_SHORT).show()
                                val homeIntent = Intent(requireContext(), HomeActivity::class.java)
                                startActivity(homeIntent)
                                requireActivity().finish()
                            } else {
                                Toast.makeText(requireContext(), "No user data found", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(requireContext(), "Error retrieving user data: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    val errorMessage = when (task.exception?.message) {
                        "The email address is badly formatted." -> "Invalid email format."
                        "There is no user record corresponding to this identifier. The user may have been deleted." -> "No account found with this email."
                        "The password is invalid or the user does not have a password." -> "Incorrect password."
                        else -> "Login failed: ${task.exception?.message}"
                    }
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

}