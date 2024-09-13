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

class SignupFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var logintTxt: TextView
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var name: TextInputEditText
    private lateinit var signUpBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logintTxt = view.findViewById(R.id.navToLogin)
        email = view.findViewById(R.id.emails)
        password = view.findViewById(R.id.passwords)
        phone = view.findViewById(R.id.phone)
        name = view.findViewById(R.id.fullname)
        signUpBtn = view.findViewById(R.id.signupBtn)

        signUpBtn.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val phoneText = phone.text.toString().trim()
            val nameText = name.text.toString().trim()

            if (emailText.isEmpty() || passwordText.isEmpty() || phoneText.isEmpty() || nameText.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            createAccount(emailText, passwordText, phoneText, nameText)
        }

        logintTxt.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    private fun createAccount(email: String, password: String, phone: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        saveUserDetails(userId, email, phone, name)
                    } else {
                        Toast.makeText(requireContext(), "Signup failed: Unable to get user ID", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorMessage = when (task.exception?.message) {
                        "The email address is already in use by another account." -> "User already exists. Please use a different email."
                        else -> "Signup failed: ${task.exception?.message}"
                    }
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUserDetails(userId: String, email: String, phone: String, name: String) {
        val userMap = hashMapOf(
            "email" to email,
            "phone" to phone,
            "name" to name
        )

        db.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Signup successful!", Toast.LENGTH_SHORT).show()
                val editor = sharedPreferences.edit()
                editor.putString("userId", userId)
                editor.putString("userName", name)
                editor.putString("userEmail", email)
                editor.putString("userPhone", phone)
                editor.apply()

                // Navigate to HomeActivity and finish the SignupActivity
                val homeIntent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(homeIntent)
                requireActivity().finish()  // Close the SignupActivity
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Error saving user details: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
