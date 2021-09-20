//package com.example.lms_app.data.comments
//
//import androidx.lifecycle.LiveData
//import com.example.lms_app.data.entities.Comment
//
//class CommentRepository(private val commentDao : ICommentDao) {
//    val allComments : LiveData<List<Comment>> = commentDao.getAllComments()
//
//    suspend fun addComment(comment : Comment){
//        commentDao.addComment(comment)
//    }
//
//    fun getCommentById(id : Int):Comment{
//        return commentDao.getCommentById(id)
//    }
//
//    fun updateComment(comment: Comment){
//        commentDao.updateComment(comment)
//    }
//
//    fun deleteComment(comment: Comment){
//        commentDao.deleteComment(comment)
//    }
//}