package com.example.lms_app.fragments.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.courses.CourseViewModel
import com.example.lms_app.data.entities.Course
import com.example.lms_app.data.entities.UserRole
import com.example.lms_app.data.users.UserViewModel
import com.example.lms_app.fragments.home.TestAdapter
import com.example.lms_app_final.R
import com.example.lms_app_final.fragments.lectures.LecturesFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class CoursesFragment : Fragment(), TestAdapter.OnItemClickListener,
    EditCourseFragment.CourseEditedSuccessfully {

    private lateinit var database: DatabaseReference
    lateinit var courseList: ArrayList<Course>

    private var courseAdapter: TestAdapter? = null
    private var editCourseFragment: EditCourseFragment? = null
    private var lecturesFragment: LecturesFragment? = null
    private lateinit var mUserRoleViewModel : UserViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var role: String
    lateinit var imageView: ImageView
    private val CourseViewModel by viewModels<CourseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        database = FirebaseDatabase.getInstance().getReference("Courses")
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val user = Firebase.auth.currentUser

        role = user?.photoUrl.toString()
        getAndShowCourses()

        return view
    }

    override fun onCourseItemClick(courseData: Course, position: Int) {
        lecturesFragment = LecturesFragment(courseData.id)
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragment_container,
            lecturesFragment!!
        )

        transaction.addToBackStack(null)

        transaction.commit()
    }

    override fun onEditClick(courseData: Course, position: Int) {
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
            courseAdapter = TestAdapter(requireContext(), role, it, this)
            recyclerView.adapter = courseAdapter
        }
    }

    override fun onCourseEdited(courseData: Course) {
        getAndShowCourses()
    }
}

