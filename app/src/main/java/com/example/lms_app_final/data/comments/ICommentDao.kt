//package com.example.lms_app.data.comments
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.lms_app.data.entities.Comment
//
//@Dao
//interface ICommentDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addComment(comment: Comment)
//
//    @Query("SELECT * FROM Comments ORDER BY Id DESC")
//    fun getAllComments() : LiveData<List<Comment>>
//
//    @Query("SELECT * FROM Comments WHERE Id = (:id)")
//    fun getCommentById(id : Int) : Comment
//
//    @Update
//    fun updateComment(comment: Comment)
//
//    @Delete
//    fun deleteComment(comment: Comment)
//}