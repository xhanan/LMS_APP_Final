//package com.example.lms_app.data
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.lms_app.data.comments.ICommentDao
//import com.example.lms_app.data.courses.ICourseDao
//import com.example.lms_app.data.entities.Comment
//import com.example.lms_app.data.entities.Course
//import com.example.lms_app.data.entities.Lecture
//import com.example.lms_app.data.entities.User
//import com.example.lms_app.data.lectures.ILectureDao
//import com.example.lms_app.data.users.IUserDao
//
//@Database(entities = [User::class, Course::class, Lecture::class, Comment::class],version = 1,exportSchema = false)
//abstract class DatabaseContext : RoomDatabase() {
//    abstract fun userDao() : IUserDao
//    abstract fun courseDao(): ICourseDao
//    abstract fun lectureDao(): ILectureDao
//    abstract fun commentDao(): ICommentDao
//    companion object{
//        @Volatile
//        private var INSTANCE : DatabaseContext? = null
//
//        fun getDatabase(context: Context) : DatabaseContext{
//            val tempInstance = INSTANCE
//            if(tempInstance != null){
//                return tempInstance
//            }
//            synchronized(this)
//            {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    DatabaseContext::class.java,
//                    "LMS_Database"
//                ).allowMainThreadQueries().build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}