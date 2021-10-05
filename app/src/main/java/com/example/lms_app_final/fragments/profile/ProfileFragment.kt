package com.example.lms_app.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lms_app.MainActivity
import com.example.lms_app.fragments.login.LoginFragment
//import com.example.lms_app_final.LoginActivity
import com.example.lms_app_final.R
import com.example.lms_app_final.fragments.lectures.LecturesFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private var logInFragment: LoginFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            view.full_name.text = user.displayName
            view.firstNameField.editText?.setText(user.displayName)
        }

        var displayName = view.firstNameField.editText?.text

        view.profile_log_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
//            val mainIntent = Intent(activity, LoginActivity::class.java)
//            startActivity(mainIntent)
        }

        view.updateFullName.setOnClickListener{
            val profileUpdates = userProfileChangeRequest {
                var displayName = view.firstNameField.editText?.text
            }

            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view.full_name.text = displayName
                        Toast.makeText(requireContext(),"Successfully updated!", Toast.LENGTH_LONG).show()
                    }
                }

            val oldPassword = view.oldPasswordField.editText?.text
            val newPassword = view.newPasswordField.editText?.text
            val confirmNewPassword = view.confirmNewPasswordField.editText?.text

            view.updatePassword.setOnClickListener{
                if(newPassword.toString() == confirmNewPassword.toString()){
                    user!!.updatePassword(newPassword.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(requireContext(),"Successfully updated!", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }

        }

        return view
    }
}