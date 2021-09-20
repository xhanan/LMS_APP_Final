package com.example.lms_app.data.courses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lms_app.data.entities.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
//    val allCourses : LiveData<List<Course>>
    private val courseRepository : CourseRepository= CourseRepository(application)
//
//    init {
//        val courseDao = DatabaseContext.getDatabase(application).courseDao()
//        courseRepository = CourseRepository(courseDao)
//        allCourses = courseRepository.allCourses
//    }
//
//    fun addCourse(course: Course){
//        viewModelScope.launch(Dispatchers.IO) {
//            courseRepository.addCourse(course)
//        }
//    }
//
//    fun getCourseById(id:Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            courseRepository.getCourseById(id)
//        }
//    }
//
//    fun updateCourse(course: Course){
//        viewModelScope.launch(Dispatchers.IO) {
//            courseRepository.updateCourse(course)
//        }
//    }

    fun deleteCourse(courseData: Course, callback: (Boolean) -> Unit){
        courseRepository.deleteCourse(courseData, callback)
    }

    public fun getCourses(callback: (ArrayList<Course>) -> Unit){
        courseRepository.getAllCourses (callback)
    }


}
