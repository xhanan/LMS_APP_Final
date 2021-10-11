package com.example.lms_app.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lms_app.MainActivity
import com.example.lms_app.fragments.register.RegisterFragment
import com.example.lms_app_final.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment() : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = Firebase.auth

        view.register_Btn.setOnClickListener{
            val registerFragment = RegisterFragment()

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container,registerFragment)
                commit()
            }
        }

        view.loginBtn.setOnClickListener{
            val user_email = login_email.text.toString()
            val user_password = login_password.text.toString()

            auth.signInWithEmailAndPassword(user_email, user_password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(requireContext(),"Successfully signed in!", Toast.LENGTH_LONG).show()
                        val user = auth.currentUser
                        val mainIntent = Intent(activity, MainActivity::class.java)
                        startActivity(mainIntent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            val mainIntent = Intent(activity, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}
