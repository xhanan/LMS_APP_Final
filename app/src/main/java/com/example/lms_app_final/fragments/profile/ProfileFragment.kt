package com.example.lms_app.fragments.profile

//import com.example.lms_app_final.LoginActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.lms_app.fragments.login.LoginFragment
import com.example.lms_app_final.Login
import com.example.lms_app_final.R
import com.example.lms_app_final.databinding.EditLectureBottomSheetBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.EmailAuthProvider.getCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase
import io.reactivex.rxjava3.internal.util.HalfSerializer.onComplete
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private var logInFragment: LoginFragment? = null
    private  lateinit var auth : FirebaseAuth

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
            Toast.makeText(requireContext(),"Signed out!", Toast.LENGTH_LONG).show()
            FirebaseAuth.getInstance().signOut()
            val mainIntent = Intent(activity, Login::class.java)
            startActivity(mainIntent)
        }

        view.updateFullName.setOnClickListener {
            val profileUpdates = userProfileChangeRequest {
                var displayName = view.firstNameField.editText?.text
            }

            user!!.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view.full_name.text = displayName
                        Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }


            view.updatePassword.setOnClickListener{

                val oldPassword = view.oldPasswordField.editText?.text
                val newPassword = view.newPasswordField.editText?.text
                val confirmNewPassword = view.confirmNewPasswordField.editText?.text

                var credential = getCredential(user!!.email.toString(), oldPassword.toString());

                if(newPassword.toString() == confirmNewPassword.toString()){
                    user!!.reauthenticate(credential)
                        .addOnCompleteListener {
                            user!!.updatePassword(newPassword.toString())
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            requireContext(),
                                            "Successfully updated!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "Password change failed!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        }
                }
            }

        return view
    }
}
