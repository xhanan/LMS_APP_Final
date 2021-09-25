package com.example.lms_app_final.fragments.lectures

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app.data.lectures.LectureRepository

class EditLectureViewModel(application: Application) : AndroidViewModel(application) {
    private val lectureRepository : LectureRepository = LectureRepository(application)
    fun submitEdit(lectureData: Lecture, callback:(Boolean) -> Unit){
        lectureRepository.editLecture(lectureData,callback)
    }
}