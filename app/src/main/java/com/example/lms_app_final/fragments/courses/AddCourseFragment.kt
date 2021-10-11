package com.example.lms_app.fragments.courses

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lms_app.data.entities.Course
import com.example.lms_app_final.R
import com.example.lms_app_final.fragments.lectures.LecturesFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_addcourse.*
import kotlinx.android.synthetic.main.fragment_addcourse.view.*

class AddCourseFragment : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference

    private var coursesFragment: CoursesFragment? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addcourse, container, false)
        auth = Firebase.auth

        database = FirebaseDatabase.getInstance().getReference("Courses")

        view.add_course_btn.setOnClickListener{
            insertCourseToDatabase(database)
        }

        return view
    }

    private fun insertCourseToDatabase(databaseReference: DatabaseReference) {
        val name = add_course_name.text.toString()
        val category = add_course_category.text.toString()
        val technology = add_course_technology.text.toString()
        val instructorId = auth.currentUser?.uid
        val imageUrl = add_course_image_url.text.toString()
        val description = add_course_description.text.toString()

        if(inputCheck(name,category,technology,instructorId.toString(),imageUrl,description)){
            val courseId = databaseReference.push().key

            val course = Course(courseId.toString(),name,category,technology,instructorId.toString(),imageUrl,description)

            if (courseId != null) {
                databaseReference.child(courseId).setValue(course).addOnSuccessListener{
                    Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
                    coursesFragment = CoursesFragment()
                    val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaction.replace(
                        R.id.fragment_container,
                        coursesFragment!!
                    )

                    transaction.addToBackStack(null)
                    transaction.commit()
                }.addOnFailureListener{
                    Toast.makeText(requireContext(),"Please fill all fields", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun inputCheck(name : String, category : String, technology : String, instructorId : String, imageUrl : String, description : String) : Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty( category) && TextUtils.isEmpty(technology) && TextUtils.isEmpty(instructorId) && TextUtils.isEmpty(imageUrl)&& TextUtils.isEmpty(description))
    }
}