package com.example.lms_app_final.fragments.lectures;

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.entities.Course
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app.data.lectures.LectureViewModel
import com.example.lms_app_final.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LecturesFragment(private val courseData: Course)  : Fragment(),
    LecturesAdapter.OnItemClickListener {

    private lateinit var database: DatabaseReference
    lateinit var lecturesList: ArrayList<Lecture>

    private var lectureAdapter: LecturesAdapter? = null

    lateinit var recyclerView: RecyclerView
    private val lectureViewModel by viewModels<LectureViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lectures, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        database = FirebaseDatabase.getInstance().getReference("Lectures")
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(view.context)

        getAndShowLectures()

        return view
    }

    fun getAndShowLectures() {
        lectureViewModel.getCourseLectures(courseData.id) {
            print(it)
            lectureAdapter = LecturesAdapter(requireContext(), it, this)
            recyclerView.adapter = lectureAdapter
        }
    }
}
