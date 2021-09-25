package com.example.lms_app.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.entities.Course
import com.example.lms_app_final.databinding.CustomRowBinding
//import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_row.view.*

class TestAdapter(val context : Context, val courseList : List<Course>,private val listener: OnItemClickListener):RecyclerView.Adapter<TestAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestAdapter.ViewHolder {
        var holder = ViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: TestAdapter.ViewHolder, position: Int) {
        holder.itemBinding.courseTitle.text = courseList[position].name
//        Picasso.get().load(courseList[position].imageUrl).into(holder.itemBinding.imageUrl)
        holder.itemView.setOnClickListener {
            listener.onCourseItemClick(courseList[position],position)
        }
        holder.itemView.editImage.setOnClickListener {
            listener.onEditClick(courseList[position],position)
        }
        holder.itemBinding.deleteImage.setOnClickListener {
            listener.onDeleteClick(courseList[position],position)
        }
    }

    override fun getItemCount(): Int = courseList.size

    inner class ViewHolder(val itemBinding: CustomRowBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    }

    interface OnItemClickListener {
        fun onCourseItemClick(courseData:Course, position: Int)
        fun onEditClick(courseData:Course, position: Int)
        fun onDeleteClick(courseData:Course, position: Int)
    }
}