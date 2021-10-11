package com.example.lms_app.data.users

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lms_app.data.DatabaseContext
import com.example.lms_app.data.entities.User
import com.example.lms_app.data.entities.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    private val repository : UserRoleRepository

    init {
        val userDao = DatabaseContext.getDatabase(application).userRoleDao()
        repository = UserRoleRepository(userDao)
    }

    fun addUserRole(userRole: UserRole){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(userRole)
        }
    }

    fun getUserRoleById(id : String): UserRole {
        return repository.getUserById(id)
    }
}