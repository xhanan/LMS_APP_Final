package com.example.lms_app.data.users

import android.content.Context
import com.example.lms_app.data.DatabaseContext
import com.example.lms_app.data.entities.User
import com.example.lms_app.data.entities.UserRole

class UserRoleRepository(context:Context,private val userRoleDao: IUserRoleDao) {

    val userDao = DatabaseContext.getInstance(context)?.userRoleDao()

    suspend fun addUser(userRole: UserRole){
        userRoleDao.addUserRole(userRole)
    }

    fun getUserById(id: String): UserRole {
        var userRole = userRoleDao.getUserById(id)
        return userRole
    }
}