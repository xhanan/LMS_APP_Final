//package com.example.lms_app.data.lectures
//
//import androidx.lifecycle.LiveData
//import com.example.lms_app.data.entities.Lecture
//
//class LectureRepository(private val lectureDao : ILectureDao) {
//    val allLectures : LiveData<List<Lecture>> = lectureDao.getAllLectures()
//
//    suspend fun addLecture(lecture: Lecture){
//        lectureDao.addLecture(lecture)
//    }
//
//    fun getLectureById(id:Int) : Lecture{
//        return lectureDao.getLectureById(id)
//    }
//
//    fun updateLecture(lecture: Lecture){
//        lectureDao.updateLecture(lecture)
//    }
//
//    fun deleteLecture(lecture: Lecture){
//        lectureDao.deleteLecture(lecture)
//    }
//}