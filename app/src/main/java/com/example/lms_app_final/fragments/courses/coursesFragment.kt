package com.example.lms_app.fragments.courses

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.example.lms_app.data.courses.CourseViewModel
import com.example.lms_app.fragments.home.HomeFragment
import com.example.lms_app.fragments.profile.ProfileFragment
import com.example.lms_app_final.R
import com.example.lms_app_final.databinding.FragmentCoursesBinding

@Suppress("DEPRECATION")
class coursesFragment : Fragment() {

    private lateinit var binding: FragmentCoursesBinding

    private val homeFragment = HomeFragment()
    private val addCourseFragment = AddCourseFragment()
    private val profileFragment = ProfileFragment()
    private val viewModel by viewModels<CourseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCoursesBinding.inflate(layoutInflater)
        val view = binding.root

        val bottomNav = binding.bottomNav

        replaceFragment(homeFragment)
        if(bottomNav != null){
            bottomNav.setOnNavigationItemSelectedListener{ item ->
                when(item.itemId){
                    R.id.nav_home -> replaceFragment(homeFragment)
                    R.id.nav_add_course -> replaceFragment(addCourseFragment)
                    R.id.nav_profile -> replaceFragment(profileFragment)
                }
                true
            }
        }
        return view
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            (activity as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        }
    }
}

