package com.example.lms_app.fragments.register

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lms_app.data.entities.User
import com.example.lms_app_final.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = Firebase.auth

        view.login_btn.setOnClickListener{

            findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
        }

        view.registerBtn.setOnClickListener{

            val email = register_email.text.toString()
            val password = register_password.text.toString()
            var firstName = register_firstName.text.toString()
            var lastName = register_lastName.text.toString()
            var phoneNumber = register_phoneNumber.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser

                        val profileUpdates = userProfileChangeRequest {
                            displayName = firstName + " " + lastName
                            phoneNumber = phoneNumber
                        }

                        user!!.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
                                }
                            }

                        Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()

                        findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
//            insertDataToDatabase(database)
        }
        return view
    }

    private fun insertDataToDatabase(databaseReference : DatabaseReference){
        val firstName = register_firstName.text.toString()
        val lastName = register_lastName.text.toString()
        val phoneNumber = register_phoneNumber.text.toString()
        val email = register_email.text.toString()
        val password = register_password.text.toString()

        if(inputCheck(firstName,lastName,phoneNumber,email,password)){
            val userId = databaseReference.push().key

            val user = User(userId.toString(),firstName,lastName,phoneNumber,email,password)

            if (userId != null) {
                databaseReference.child(userId).setValue(user).addOnSuccessListener {
                    Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_register_fragment_to_login_fragment)
                }.addOnFailureListener{
                    Toast.makeText(requireContext(),"Please fill all fields", Toast.LENGTH_LONG).show()
                }
            }
        }else{
            Toast.makeText(requireContext(),"Please fill all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(fName : String, lName : String, pNumber : String, email : String, password : String) : Boolean{
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && TextUtils.isEmpty(pNumber) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }
}