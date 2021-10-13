package com.example.lms_app_final.fragments.lectures;

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.DatabaseContext
import com.example.lms_app.data.entities.Course
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app.data.entities.UserRole
import com.example.lms_app.data.lectures.LectureViewModel
import com.example.lms_app.data.users.UserViewModel
import com.example.lms_app.fragments.courses.EditCourseFragment
import com.example.lms_app_final.R
import com.example.lms_app_final.fragments.singlelecture.SingleLectureFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_lectures.view.*


class LecturesFragment(private val courseId: String) : Fragment(),
    LecturesAdapter.OnItemClickListener, EditLectureFragment.LectureEditedSuccessfully{

    private lateinit var database: DatabaseReference
    lateinit var lecturesList: ArrayList<Lecture>
    lateinit var role: String
    private var editLectureFragment: EditLectureFragment? = null
    private var addLectureFragment: AddLectureFragment? = null

    private lateinit var mUserRoleViewModel : UserViewModel

    private var lectureAdapter: LecturesAdapter? = null

    lateinit var recyclerView: RecyclerView
    private val lectureViewModel by viewModels<LectureViewModel>()
    private var singleLectureFragment : SingleLectureFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lectures, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        database = FirebaseDatabase.getInstance().getReference("Lectures")
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        mUserRoleViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val user = Firebase.auth.currentUser

        var userRole = UserRole()
        user?.let {
            userRole = activity?.let { it1 -> DatabaseContext.getDatabase(it1.applicationContext)?.userRoleDao()?.getUserById(user?.uid!!) }!!
        }

        role = userRole.role
        getAndShowLectures()

        if(!role.equals("INSTRUCTOR"))
        {
            view.floatingActionButton.visibility = View.INVISIBLE
        }
        view.floatingActionButton.setOnClickListener {
            onActionClick(courseId)
        }

        return view
    }

    fun getAndShowLectures() {
        lectureViewModel.getCourseLectures(courseId) {
            print(it)
            lectureAdapter = LecturesAdapter(requireContext(), role, it, this)
            recyclerView.adapter = lectureAdapter
        }
    }

    override fun onCourseItemClick(lectureData: Lecture, position: Int) {
        singleLectureFragment = SingleLectureFragment(lectureData)
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragment_container,
            singleLectureFragment!!
        )

        transaction.addToBackStack(null)

        transaction.commit()
    }

    override fun onEditClick(lectureData: Lecture, position: Int) {
        editLectureFragment = EditLectureFragment(lectureData, this)
        editLectureFragment?.show(childFragmentManager,"EditLectureFragment")
    }

    override fun onDeleteClick(lectureData: Lecture, position: Int) {
        lectureViewModel.deleteLecture(lectureData) {
            if (it) {
                Toast.makeText(context, "Lecture Deleted Successfully", Toast.LENGTH_LONG).show()
                getAndShowLectures()
            } else {
                Toast.makeText(context, "Lecture not Deleted", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onLecutreEdited(lectureData: Lecture) {
        getAndShowLectures()
    }

    fun onActionClick(courseId: String) {
        addLectureFragment = AddLectureFragment(courseId)
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        transaction.replace(
            R.id.fragment_container,
            addLectureFragment!!
        )

        transaction.addToBackStack(null)

        transaction.commit()
    }

    private  fun getUserRole(userId: String): UserRole {
        return mUserRoleViewModel.getUserRoleById(userId)
    }
}
