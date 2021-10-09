package com.example.lms_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lms_app.fragments.courses.AddCourseFragment
import com.example.lms_app.fragments.courses.CoursesFragment
import com.example.lms_app.fragments.home.HomeFragment
import com.example.lms_app.fragments.profile.ProfileFragment
import com.example.lms_app_final.R
import com.example.lms_app_final.fragments.lectures.AddLectureFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment=HomeFragment()
        val coursesFragment=CoursesFragment()
        val addCourseFragment=AddCourseFragment()
        val addLectureFragment= AddLectureFragment()
        val profileFragment=ProfileFragment()

        setCurrentFragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->setCurrentFragment(homeFragment)
                R.id.nav_courses ->setCurrentFragment(coursesFragment)
                R.id.nav_add_course ->setCurrentFragment(addCourseFragment)
                R.id.nav_add_lecture ->setCurrentFragment(addLectureFragment)
                R.id.nav_profile ->setCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }
}