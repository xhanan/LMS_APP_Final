package com.example.lms_app_final.fragments.lectures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app_final.databinding.EditLectureBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditLectureFragment(private val lectureData: Lecture, private val listener: LectureEditedSuccessfully) :
    BottomSheetDialogFragment(){

    private var binding: EditLectureBottomSheetBinding? = null
    private val viewModel by viewModels<EditLectureViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EditLectureBottomSheetBinding.inflate(inflater, container, false);

        binding?.lectureTitle?.setText(lectureData.title)
        binding?.lectureThumbnailUrl?.setText(lectureData.thumbnailUrl)
        binding?.lecturesDesc?.setText(lectureData.description)
        binding?.lectureVideoUrl?.setText(lectureData.videoUrl)

        binding?.editLectureButton?.setOnClickListener {
            viewModel.submitEdit(
                Lecture(
                    lectureData.id,
                binding?.lectureTitle?.text.toString(),
                binding?.lectureThumbnailUrl?.text.toString(),
                binding?.lecturesDesc?.text.toString(),
                binding?.lectureVideoUrl?.text.toString()
            )
            ){
                    lectureEditedSuccessfully ->
                if (lectureEditedSuccessfully) { listener.onLecutreEdited(lectureData) }
            }
        }

        return binding!!.root
    }
    interface LectureEditedSuccessfully {
        fun onLecutreEdited(lectureData: Lecture)
    }
}