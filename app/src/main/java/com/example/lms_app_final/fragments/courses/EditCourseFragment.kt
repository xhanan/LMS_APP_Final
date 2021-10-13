package com.example.lms_app.fragments.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lms_app.data.courses.EditCourseViewModel
import com.example.lms_app.data.entities.Course
import com.example.lms_app_final.databinding.EditCourseBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditCourseFragment(private val courseData: Course,private val listener: CourseEditedSuccessfully) :BottomSheetDialogFragment(){
    private var binding: EditCourseBottomSheetBinding? = null
    private val viewModel by viewModels<EditCourseViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EditCourseBottomSheetBinding.inflate(inflater, container, false);

        binding?.courseCategory?.setText(courseData.category)
        binding?.courseDescription?.setText(courseData.description)
        binding?.courseImage?.setText(courseData.imageUrl)
        binding?.courseName?.setText(courseData.name)
        binding?.courseTechnology?.setText(courseData.technology)

        binding?.editCourseButton?.setOnClickListener {
            viewModel.submitEdit(Course(
                courseData.id,
                binding?.courseName?.text.toString(),
                binding?.courseCategory?.text.toString(),
                binding?.courseTechnology?.text.toString(),
                binding?.courseImage?.text.toString(),
                binding?.courseDescription?.text.toString(),
                )){
                    courseEditetSuccessfully ->
                if (courseEditetSuccessfully) { listener.onCourseEdited(courseData) }
            }
        }

        return binding!!.root
    }
    interface CourseEditedSuccessfully {
        fun onCourseEdited(courseData: Course)
    }
}