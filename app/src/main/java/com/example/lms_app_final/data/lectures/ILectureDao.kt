//package com.example.lms_app.data.lectures
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.lms_app.data.entities.Lecture
//
//@Dao
//interface ILectureDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addLecture(lecture: Lecture)
//
//    @Query("SELECT * FROM LECTURES ORDER BY Id ASC")
//    fun getAllLectures() : LiveData<List<Lecture>>
//
//    @Query("SELECT * FROM Lectures WHERE Id = (:id)")
//    fun getLectureById(id:Int) : Lecture
//
//    @Update
//    fun updateLecture(lecture: Lecture)
//
//    @Delete
//    fun deleteLecture(lecture: Lecture)
//}