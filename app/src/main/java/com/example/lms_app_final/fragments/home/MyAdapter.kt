package com.example.lms_app.fragments.home

import com.example.lms_app_final.R
import android.content.Context

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.entities.Course
import kotlinx.android.synthetic.main.custom_row.view.*


class MyAdapter(c: Context, courseList: ArrayList<Course>) :
    RecyclerView.Adapter<MyViewHolder>() {
    var c: Context
    var courseList: ArrayList<Course>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v: View = LayoutInflater.from(c).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.course_title.text = courseList[position].name
        holder.itemView.course_id.text = courseList[position].id
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    init {
        this.c = c
        this.courseList = courseList
    }
}