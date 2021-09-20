package com.example.lms_app.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lms_app_final.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.addBtn.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val firstName = firstName.text.toString()
        val lastName = lastName.text.toString()
        val phoneNumber = phoneNumber.text.toString()
        val email = email.text.toString()
        val password = password.text.toString()

        if(inputCheck(firstName,lastName,phoneNumber,email,password)){
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill all fields",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(fName : String, lName : String, pNumber : String, email : String, password : String) : Boolean{
        return !(TextUtils.isEmpty(fName) && TextUtils.isEmpty(lName) && TextUtils.isEmpty(pNumber) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
    }
}