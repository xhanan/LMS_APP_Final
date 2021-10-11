package com.example.lms_app_final.fragments.lectures

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.lms_app.data.entities.Course
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app.fragments.courses.CoursesFragment
import com.example.lms_app_final.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_addcourse.*
import kotlinx.android.synthetic.main.fragment_addcourse.view.*
import kotlinx.android.synthetic.main.fragment_addlecture.*

class AddLectureFragment(private val courseId: String) : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference

    private var lecturesFragment: LecturesFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_addlecture, container, false)
        auth = Firebase.auth

        database = FirebaseDatabase.getInstance().getReference("Lectures")

        view.add_course_btn.setOnClickListener{
            insertCourseToDatabase(database)
        }

        return view
    }

    private fun insertCourseToDatabase(databaseReference: DatabaseReference) {
        val title = add_lecture_Title.text.toString()
        val thumbnailUrl = add_lecture_thumbnail.text.toString()
        val videoUrl = add_video_url.text.toString().split("v=")[1]
        var description = add_lecture_desc.text.toString()

        if(inputCheck(title,description,thumbnailUrl,videoUrl)){
            val lectureId = databaseReference.push().key
            val lecture = Lecture(lectureId.toString(),title, courseId, videoUrl, thumbnailUrl,description)

            if (lectureId != null) {
                databaseReference.child(lectureId).setValue(lecture).addOnSuccessListener{
                    Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_LONG).show()
                    lecturesFragment = LecturesFragment(courseId)
                    val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                    transaction.replace(
                        R.id.fragment_container,
                        lecturesFragment!!
                    )

                    transaction.addToBackStack(null)
                    transaction.commit()
                }.addOnFailureListener{
                    Toast.makeText(requireContext(),"Please fill all fields", Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun inputCheck(title : String, description : String, thumbnailUrl : String, videoUrl : String) : Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(thumbnailUrl) && TextUtils.isEmpty(videoUrl))
    }
}