package com.example.lms_app

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lms_app.data.entities.UserRole
import com.example.lms_app.data.users.UserViewModel
import com.example.lms_app.fragments.courses.AddCourseFragment
import com.example.lms_app.fragments.courses.CoursesFragment
import com.example.lms_app.fragments.home.HomeFragment
import com.example.lms_app.fragments.profile.ProfileFragment
import com.example.lms_app_final.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var mUserRoleViewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadLocale()
        mUserRoleViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val user = Firebase.auth.currentUser

//        var userRole = UserRole()
//        user?.let {
//            userRole = getUserRole(it.uid)
//        }
//
//        if(userRole.role.equals("INSTRUCTOR"))
//            bottom_nav.menu.findItem(R.id.nav_add_course).isVisible = false

        val homeFragment=HomeFragment()
        val coursesFragment=CoursesFragment()
        val addCourseFragment=AddCourseFragment()
        val profileFragment=ProfileFragment()

        setCurrentFragment(homeFragment)

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home->setCurrentFragment(homeFragment)
                R.id.nav_courses ->setCurrentFragment(coursesFragment)
                R.id.nav_add_course ->setCurrentFragment(addCourseFragment)
                R.id.nav_profile ->setCurrentFragment(profileFragment)
            }
            true
        }
    }


    fun changeLocale(Lang: String) {

        val locale = Locale(Lang)

        Locale.setDefault(locale)

        val config = Configuration()

        config.locale = locale
        baseContext.resources?.updateConfiguration(config, applicationContext?.resources?.displayMetrics)

        val editor = baseContext?.getSharedPreferences("Settings", Context.MODE_PRIVATE)?.edit()
        editor?.putString("My_Lang", Lang)
        editor?.apply()
        changeBottomNavText()
    }

    private fun loadLocale() {
        val sharedPreferences = applicationContext?.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        var language = sharedPreferences?.getString("My_Lang", "")
        if (language != null) {
            changeLocale(language)
        }
    }

    private fun changeBottomNavText(){
        bottom_nav.menu.findItem(R.id.nav_add_course).title =  getString(R.string.addCourseText)
        bottom_nav.menu.findItem(R.id.nav_home).title =  getString(R.string.homeText)
        bottom_nav.menu.findItem(R.id.nav_profile).title =  getString(R.string.profileText)
    }

    private  fun getUserRole(userId: String): UserRole {
        return mUserRoleViewModel.getUserRoleById(userId)
    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_riht_to_left,R.anim.enter_left_to_right,R.anim.exit_left_to_right)
            replace(R.id.fragment_container,fragment)
            commit()
        }
}