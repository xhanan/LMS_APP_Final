package com.example.lms_app.data.lectures
//
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lms_app.data.entities.Lecture

class LectureViewModel(application: Application):AndroidViewModel(application) {

    private val lectureRepository : LectureRepository = LectureRepository(application)

    public fun getCourseLectures(courseId: String, callback: (ArrayList<Lecture>) -> Unit){
        lectureRepository.getAllCourseLectures (courseId, callback)
    }
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
}