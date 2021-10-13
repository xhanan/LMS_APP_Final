package com.example.lms_app.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lms_app.data.entities.*
import com.example.lms_app.data.users.IUserRoleDao
import com.google.firebase.firestore.auth.User

@Database(entities = [UserRole::class], version = 1, exportSchema = false)
abstract class DatabaseContext : RoomDatabase() {

    abstract fun userRoleDao() : IUserRoleDao

    companion object {
        private var INSTANCE: DatabaseContext? = null

        fun getInstance(context: Context): DatabaseContext? {
            if (INSTANCE == null) {
                synchronized(DatabaseContext::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DatabaseContext::class.java, "MLS").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}