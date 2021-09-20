package com.example.lms_app.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.courses.CourseViewModel
import com.example.lms_app.data.entities.Course
import com.example.lms_app.fragments.courses.EditCourseFragment
import com.example.lms_app_final.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.custom_row.view.*

class HomeFragment : Fragment(), TestAdapter.OnItemClickListener,
    EditCourseFragment.CourseEditedSuccessfully {

    private lateinit var database: DatabaseReference
    lateinit var courseList: ArrayList<Course>

    private var courseAdapter: TestAdapter? = null
    private var editCourseFragment: EditCourseFragment? = null

    lateinit var recyclerView: RecyclerView
    private val CourseViewModel by viewModels<CourseViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        database = FirebaseDatabase.getInstance().getReference("Courses")
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        getAndShowCourses()

        return view
    }

    override fun onCourseItemClick(courseData: Course, position: Int) {
        editCourseFragment = EditCourseFragment(courseData, this)
        editCourseFragment?.show(childFragmentManager,"EditCourseFragment")
    }

    override fun onDeleteClick(courseData: Course, position: Int) {
        CourseViewModel.deleteCourse(courseData) {
            if (it) {
                Toast.makeText(context, "Course Deleted Successfully", Toast.LENGTH_LONG).show()
                getAndShowCourses()
            } else {
                Toast.makeText(context, "Course not Deleted", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun getAndShowCourses() {
        CourseViewModel.getCourses {
            print(it)
            courseAdapter = TestAdapter(requireContext(), it, this)
            recyclerView.adapter = courseAdapter
        }
    }

    override fun onCourseEdited(courseData: Course) {
        getAndShowCourses()
    }
}
