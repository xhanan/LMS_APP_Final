package com.example.lms_app.fragments.home

import android.view.View

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.custom_row.view.*


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titleTxt: TextView
    var idTxt: TextView

    init {
        titleTxt = itemView.course_title
        idTxt = itemView.course_id
    }
}