package com.example.lms_app.data.users

import androidx.room.*
import com.example.lms_app.data.entities.User
import com.example.lms_app.data.entities.UserRole

@Dao
interface IUserRoleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserRole(userRole: UserRole)

    @Query("SELECT * FROM UserRole WHERE id = (:userId)")
    fun getUserById(userId : String) : UserRole
}