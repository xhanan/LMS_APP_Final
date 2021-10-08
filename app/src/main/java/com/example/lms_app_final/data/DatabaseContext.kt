package com.example.lms_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lms_app.data.entities.*
import com.example.lms_app.data.users.IUserRoleDao

@Database(entities = [UserRole::class],version = 1,exportSchema = false)
abstract class DatabaseContext : RoomDatabase() {
    abstract fun userRoleDao() : IUserRoleDao
    companion object{
        @Volatile
        private var INSTANCE : DatabaseContext? = null

        fun getDatabase(context: Context) : DatabaseContext{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseContext::class.java,
                    "LMS_Database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}