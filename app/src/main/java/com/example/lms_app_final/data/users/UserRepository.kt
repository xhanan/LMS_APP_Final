package com.example.lms_app.data.users

import com.example.lms_app.data.entities.User
import com.example.lms_app.data.entities.UserRole

class UserRoleRepository(private val userRoleDao: IUserRoleDao) {
    suspend fun addUser(userRole: UserRole){
        userRoleDao.addUserRole(userRole)
    }

//    fun getUserById(id: Int): User {
//        return userRoleDao.getUserById(id)
//    }
}