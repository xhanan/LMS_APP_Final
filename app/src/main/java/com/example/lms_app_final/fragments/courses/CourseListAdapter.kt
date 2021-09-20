 package com.example.lms_app.fragments.courses

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.entities.Course
import com.example.lms_app_final.R
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL


 class CourseListAdapter(val mcontext : Context, var coursesList : List<Course>) : RecyclerView.Adapter<CourseListAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = coursesList[position]
        holder.itemView.findViewById<TextView>(R.id.course_id).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.course_title).text = currentItem.name.toString()

        getBitmapFromUrl(holder,currentItem.imageUrl)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    fun setData(courses:List<Course>){
        this.coursesList = courses
        notifyDataSetChanged()
    }

     private fun getBitmapFromUrl(holder : MyViewHolder,imageUrl: String) {
         CoroutineScope(Dispatchers.IO).launch {
             val bitmap = getBitmap(imageUrl)

             withContext(Dispatchers.Main) {
                 holder.itemView.image_url.setImageBitmap(bitmap)
             }
         }
     }

     private fun getBitmap(imageUrl :String): Bitmap? {
         try{
             val url = URL(imageUrl)
             return BitmapFactory.decodeStream(url.openConnection().getInputStream())
         }catch (e : IOException){
             return null
         }
     }
}
