package com.example.lms_app.data.lectures

import android.app.Application
import android.util.Log
import com.example.lms_app.data.entities.Course
import com.example.lms_app.data.entities.Lecture
import com.google.firebase.database.*

class LectureRepository(var application: Application) {
    private lateinit var database: DatabaseReference
    var lectures = ArrayList<Lecture>()

    fun getAllCourseLectures(courseId:String, callback: (ArrayList<Lecture>) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("Lectures")

        database.orderByChild("courseId").equalTo(courseId).addListenerForSingleValueEvent(object : ValueEventListener {
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

    fun deleteLecture(lectureData: Lecture, callback: (Boolean) -> Unit) {

        database = FirebaseDatabase.getInstance().getReference("Lectures")
        database.child(lectureData.id).removeValue().addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            Log.i("Failed to delete data", "Error: $it")
            callback(false)
        }
    }

    fun editLecture(lectureData: Lecture, callback: (Boolean) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("Lectures")
        database.child(lectureData.id).setValue(lectureData).addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            Log.i("Failed to edit data", "Error: $it")
            callback(false)
        }
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
