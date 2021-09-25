package com.example.lms_app.data.courses

import android.app.Application
import android.util.Log
import com.example.lms_app.data.entities.Course
import com.google.firebase.database.*


class CourseRepository(var application: Application) {
    private lateinit var database: DatabaseReference
    var courses = ArrayList<Course>()

    //    val allCourses : LiveData<List<Course>> = courseDao.getAllCourses()
//
//    suspend fun addCourse(course: Course){
//        courseDao.addCourse(course)
//    }
//
//    fun getCourseById(id : Int):Course{
//        return courseDao.getByIdAsync(id)
//    }
//
//    fun updateCourse(course: Course){
//        courseDao.updateCourse(course)
//    }
//
//    fun deleteCourse(course: Course){
//        courseDao.deleteCourse(course)
//    }

    fun editCourse(courseData: Course, callback: (Boolean) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("Courses")
        database.child(courseData.id).setValue(courseData).addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            Log.i("Failed to edit data", "Error: $it")
            callback(false)
        }
    }

    fun getAllCourses(callback: (ArrayList<Course>) -> Unit) {
        database = FirebaseDatabase.getInstance().getReference("Courses")
        courses.clear()
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnap in snapshot.children) {
                        val course = dataSnap.getValue(Course::class.java)
                        courses?.add(course!!)
                    }
                    callback(courses!!)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }

    fun deleteCourse(courseData: Course, callback: (Boolean) -> Unit) {

        database = FirebaseDatabase.getInstance().getReference("Courses")
        database.child(courseData.id).removeValue().addOnSuccessListener {
            callback(true)
        }.addOnFailureListener {
            Log.i("Failed to delete data", "Error: $it")
            callback(false)
        }
    }
}