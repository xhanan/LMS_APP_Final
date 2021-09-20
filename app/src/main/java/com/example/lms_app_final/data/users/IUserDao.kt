//package com.example.lms_app.data.users
//
//import androidx.lifecycle.LiveData
//import androidx.room.*
//import com.example.lms_app.data.entities.User
//
//@Dao
//interface IUserDao {
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun addUser(user: User)
//
//    @Query("SELECT * FROM Users ORDER BY Id ASC")
//    fun getAllUsers() : LiveData<List<User>>
//
//    @Query("SELECT * FROM Users WHERE Id = (:userId)")
//    fun getUserById(userId : Int) : User
//
//    @Update
//    fun updateUser(user : User)
//
//    @Delete
//    fun deleteUser(user : User)
//
//    @Query("SELECT * FROM Users WHERE Email = (:email) AND Password = (:password)")
//    fun loginUser(email : String, password : String) : User
//}