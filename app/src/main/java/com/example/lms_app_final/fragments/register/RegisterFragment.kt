package com.example.lms_app.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lms_app.MainActivity
import com.example.lms_app.data.entities.UserRole
import com.example.lms_app.data.users.UserViewModel
import com.example.lms_app.fragments.login.LoginFragment
import com.example.lms_app_final.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    private lateinit var mUserRoleViewModel : UserViewModel

    private var loginFragment: LoginFragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        auth = Firebase.auth
        mUserRoleViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

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
                                    insertDataToDatabase(user.providerId);
                                    Toast.makeText(requireContext(),"Successfully registered!", Toast.LENGTH_LONG).show()
                                }
                            }

                        loginFragment = LoginFragment()
                        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                        transaction.replace(
                            R.id.fragment_container,
                            loginFragment!!
                        )

                        transaction.addToBackStack(null)
                        transaction.commit()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return view
    }

    private  fun insertDataToDatabase(userId : String){
        val isInstructor = isInstructor.isChecked

        val role = if (isInstructor) "INSTRUCTOR" else "STUDENT"
        val userRole = UserRole(userId, role)

        mUserRoleViewModel.addUserRole(userRole)
    }
}