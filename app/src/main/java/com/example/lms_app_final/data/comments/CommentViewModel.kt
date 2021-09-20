//package com.example.lms_app.data.comments
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import com.example.lms_app.data.DatabaseContext
//import com.example.lms_app.data.entities.Comment
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class CommentViewModel(application: Application) : AndroidViewModel(application) {
//    private val allComments : LiveData<List<Comment>>
//    private val commentRepository : CommentRepository
//
//    init {
//        val commentDao = DatabaseContext.getDatabase(application).commentDao()
//        commentRepository = CommentRepository(commentDao)
//        allComments = commentRepository.allComments
//    }
//
//    suspend fun addComment(comment: Comment){
//        viewModelScope.launch(Dispatchers.IO) {
//            commentRepository.addComment(comment)
//        }
//    }
//
//    fun getCommentById(id: Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            commentRepository.getCommentById(id)
//        }
//    }
//
//    fun updateComment(comment: Comment){
//        viewModelScope.launch(Dispatchers.IO) {
//            commentRepository.updateComment(comment)
//        }
//    }
//
//    fun delteComment(comment: Comment){
//        viewModelScope.launch(Dispatchers.IO) {
//            commentRepository.deleteComment(comment)
//        }
//    }
//}