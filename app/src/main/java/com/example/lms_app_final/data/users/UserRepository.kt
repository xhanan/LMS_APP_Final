//package com.example.lms_app.data.users
//
//import androidx.lifecycle.LiveData
//import com.example.lms_app.data.entities.User
//
//class UserRepository(private val userDao: IUserDao) {
//    suspend fun addUser(user: User){
//        userDao.addUser(user)
//    }
//
//    val allUsers : LiveData<List<User>> = userDao.getAllUsers()
//
//    fun getUserById(id: Int): User {
//        return userDao.getUserById(id)
//    }
//
//    fun updateUser(user:User){
//        userDao.updateUser(user)
//    }
//
//    fun deleteUser(user:User){
//        userDao.deleteUser(user)
//    }
//
//    fun loginUser(email:String,password:String) : User{
//        return userDao.loginUser(email,password)
//    }
//}