package com.example.lms_app_final.fragments.lectures

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app.fragments.home.TestAdapter
import com.example.lms_app_final.databinding.LectureListBinding
import com.squareup.picasso.Picasso

class LecturesAdapter(val context: Context, val lectureList: List<Lecture>, private val listener: OnItemClickListener):RecyclerView.Adapter<LecturesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LecturesAdapter.ViewHolder {
        var holder = ViewHolder(LectureListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        return holder
    }

    override fun onBindViewHolder(holder: LecturesAdapter.ViewHolder, position: Int) {
        holder.itemBinding.lectureTitle.text = lectureList[position].Title
        Picasso.get().load(lectureList[position].ThumbnailUrl).into(holder.itemBinding.thumbnailUrl)
        holder.itemBinding.lectureDate.text = lectureList[position].Description
    }

    override fun getItemCount(): Int = lectureList.size

    inner class ViewHolder(val itemBinding: LectureListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    }

    interface OnItemClickListener {
    }
}