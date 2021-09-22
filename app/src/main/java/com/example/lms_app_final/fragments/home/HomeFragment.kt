package com.example.lms_app.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.courses.CourseViewModel
import com.example.lms_app.data.entities.Course
import com.example.lms_app.fragments.courses.AddCourseFragment
import com.example.lms_app.fragments.courses.CoursesFragment
import com.example.lms_app.fragments.courses.EditCourseFragment
import com.example.lms_app.fragments.profile.ProfileFragment
import com.example.lms_app_final.R
import com.example.lms_app_final.databinding.FragmentCoursesBinding
import com.example.lms_app_final.databinding.FragmentHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.custom_row.view.*

class HomeFragment : Fragment(R.layout.fragment_home){
}
