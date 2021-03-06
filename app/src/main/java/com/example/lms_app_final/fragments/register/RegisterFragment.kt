package com.example.lms_app.fragments.register

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lms_app.data.entities.UserRole
import com.example.lms_app.data.users.UserViewModel
import com.example.lms_app.fragments.login.LoginFragment
import com.example.lms_app_final.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*
import java.lang.Exception

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
            loginFragment = LoginFragment()
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(
                R.id.fragment_container,
                loginFragment!!
            )

            transaction.addToBackStack(null)
            transaction.commit()
        }



        view.registerBtn.setOnClickListener{

            val email = register_email.text.toString()
            val password = register_password.text.toString()
            var firstName = register_firstName.text.toString()
            var lastName = register_lastName.text.toString()
            var phoneNumber = register_phoneNumber.text.toString()
            val isInstructor = view?.isInstructor?.isChecked ?: false
            val role = if (isInstructor) "INSTRUCTOR" else "STUDENT"
            var isCompleted = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        isCompleted = true
                        val user = auth.currentUser

                        val profileUpdates = userProfileChangeRequest {
                            photoUri = Uri.parse(role)
                            displayName = firstName + " " + lastName
                            phoneNumber = phoneNumber
                        }

                        user!!.updateProfile(profileUpdates)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    //insertDataToDatabase(user.uid,isInstructor);
                                    Toast.makeText(requireContext(),"Profile updated", Toast.LENGTH_LONG).show()
                                }
                            }
                        Toast.makeText(requireContext(), "Registered Successfully.",Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { object : OnFailureListener{
                    override fun onFailure(p0: Exception) {
                        print(p0)
                    }

                } }
        }
        return view
    }

//    private  fun insertDataToDatabase(userId : String, isInstructor : Boolean){
//        val role = if (isInstructor) "INSTRUCTOR" else "STUDENT"
//        val userRole = UserRole(userId, role)
//
//        mUserRoleViewModel.addUserRole(userRole)
//    }
}