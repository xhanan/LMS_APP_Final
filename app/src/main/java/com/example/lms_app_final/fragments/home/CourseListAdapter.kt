//package com.example.lms_app.fragments.home
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.lms_app.R
//import com.example.lms_app.data.entities.Course
//import com.example.lms_app.fragments.courses.CourseListAdapter
//import kotlinx.android.synthetic.main.custom_row.view.*
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import java.io.IOException
//import java.net.URL
//
//class CourseListAdapter(private val context : Context,
//                        private val dataSource: ArrayList<Course>): RecyclerView.Adapter<com.example.lms_app.fragments.home.CourseListAdapter.MyViewHolder>() {
//
//    private val inflater: LayoutInflater
//            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//
//    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
//    }
//
//    override fun getItemCount(): Int {
//        return dataSource.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val currentItem = dataSource[position]
//        holder.itemView.course_id.text = currentItem.Id.toString()
//        holder.itemView.course_title.text = currentItem.Name.toString()
//
//        getBitmapFromUrl(holder,currentItem.ImageUrl)
//    }
//
//    private fun getBitmapFromUrl(holder : MyViewHolder, imageUrl: String) {
//        CoroutineScope(Dispatchers.IO).launch {
//            val bitmap = getBitmap(imageUrl)
//
//            withContext(Dispatchers.Main) {
//                holder.itemView.image_url.setImageBitmap(bitmap)
//            }
//        }
//    }
//
//    private fun getBitmap(imageUrl :String): Bitmap? {
//        try{
//            val url = URL(imageUrl)
//            return BitmapFactory.decodeStream(url.openConnection().getInputStream())
//        }catch (e : IOException){
//            return null
//        }
//    }
//
//
//
//}