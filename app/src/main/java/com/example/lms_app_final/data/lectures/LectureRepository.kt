package com.example.lms_app.data.lectures

import android.app.Application
import com.example.lms_app.data.entities.Lecture
import com.google.firebase.database.*

class LectureRepository(var application: Application) {
    private lateinit var database: DatabaseReference
    var lectures = ArrayList<Lecture>()

    fun getAllCourseLectures(courseId:String, callback: (ArrayList<Lecture>) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("Lectures")
        lectures.clear()
        database.orderByChild("CourseId").equalTo(courseId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnap in snapshot.children) {
                        val lecture = dataSnap.getValue(Lecture::class.java)
                        lectures?.add(lecture!!)
                    }
                    callback(lectures!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

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
}