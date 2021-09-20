//package com.example.lms_app.data.courses
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.lms_app.data.entities.Course
//
//@Dao
//interface ICourseDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addCourse(course: Course)
//
//    @Query("SELECT * FROM Courses ORDER BY Id ASC")
//    fun getAllCourses() : LiveData<List<Course>>
//
//    @Query("SELECT * FROM Courses WHERE Id = (:id)")
//    fun getByIdAsync(id : Int) : Course
//
//    @Update
//    fun updateCourse(course: Course)
//
//    @Delete
//    fun deleteCourse(course: Course)
//}