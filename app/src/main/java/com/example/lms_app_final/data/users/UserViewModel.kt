//package com.example.lms_app.data.users
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import com.example.lms_app.data.DatabaseContext
//import com.example.lms_app.data.entities.User
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class UserViewModel(application: Application):AndroidViewModel(application) {
//    private val allUsers : LiveData<List<User>>
//    private val repository : UserRepository
//
//    init {
//        val userDao = DatabaseContext.getDatabase(application).userDao()
//        repository = UserRepository(userDao)
//        allUsers = repository.allUsers
//    }
//
//    fun addUser(user: User){
//        viewModelScope.launch (Dispatchers.IO){
//            repository.addUser(user)
//        }
//    }
//
//    fun getUserById(id : Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getUserById(id)
//        }
//    }
//
//    fun updateUser(user:User){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.updateUser(user)
//        }
//    }
//
//    fun deleteUser(user: User){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.deleteUser(user)
//        }
//    }
//
//    fun loginUser(email:String,password:String) : Boolean{
//        var doesExists = false
//        viewModelScope.launch{
//            val user = repository.loginUser(email,password)
//            if(user != null){
//                doesExists = true
//            }
//        }
//        return doesExists
//    }
//}