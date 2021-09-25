package com.example.lms_app.data.courses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.lms_app.data.entities.Course

class EditCourseViewModel(application: Application) : AndroidViewModel(application) {
    private val courseRepository : CourseRepository= CourseRepository(application)
    fun submitEdit(courseData: Course, callback:(Boolean) -> Unit){
        courseRepository.editCourse(courseData,callback)
    }
}