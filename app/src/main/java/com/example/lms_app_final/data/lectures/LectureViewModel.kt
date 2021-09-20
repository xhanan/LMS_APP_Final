//package com.example.lms_app.data.lectures
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import com.example.lms_app.data.DatabaseContext
//import com.example.lms_app.data.entities.Lecture
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class LectureViewModel(application: Application):AndroidViewModel(application) {
//    private val allLectures : LiveData<List<Lecture>>
//    private val lectureRepository : LectureRepository
//
//    init {
//        val lectureDao = DatabaseContext.getDatabase(application).lectureDao()
//        lectureRepository = LectureRepository(lectureDao)
//        allLectures = lectureRepository.allLectures
//    }
//
//    fun addLecture(lecture: Lecture){
//        viewModelScope.launch (Dispatchers.IO){
//            lectureRepository.addLecture(lecture)
//        }
//    }
//
//    fun getLectureById(id: Int){
//        viewModelScope.launch (Dispatchers.IO){
//            lectureRepository.getLectureById(id)
//        }
//    }
//
//    fun updateLecture(lecture: Lecture){
//        viewModelScope.launch (Dispatchers.IO){
//            lectureRepository.updateLecture(lecture)
//        }
//    }
//
//    fun getLectureById(lecture: Lecture){
//        viewModelScope.launch (Dispatchers.IO){
//            lectureRepository.deleteLecture(lecture)
//        }
//    }
//}