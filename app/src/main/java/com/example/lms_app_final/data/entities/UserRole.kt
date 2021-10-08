package com.example.lms_app.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserRole")
data class UserRole (
    @PrimaryKey()
    val id : String = "",
    val role : String = ""
)