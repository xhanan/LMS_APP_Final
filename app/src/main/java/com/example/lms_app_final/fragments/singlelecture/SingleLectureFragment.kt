package com.example.lms_app_final.fragments.singlelecture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.lms_app.data.entities.Lecture
import com.example.lms_app.data.lectures.LectureViewModel
import com.example.lms_app_final.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_single_lecture.view.*


class SingleLectureFragment(private val lectureData: Lecture)  : Fragment(){

    lateinit var lecturesList: ArrayList<Lecture>

    lateinit var recyclerView: RecyclerView
    private val lectureViewModel by viewModels<LectureViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_lecture, container, false)

        lectureViewModel.getCourseLecture(lectureData){
            print(lectureData)
            view.single_lecture_title.text = lectureData.title
            view.single_lecture_desc.text = lectureData.description

            val youTubePlayerView: YouTubePlayerView = view.single_lecture_video
            lifecycle.addObserver(youTubePlayerView)

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val videoId = lectureData.videoUrl
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }

        return view
    }
}