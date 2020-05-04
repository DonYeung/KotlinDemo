package com.example.app.mediaplayerdemo

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_mp4demo.*

class Mp4PlayerActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mp4demo)

        val uri = Uri.parse("android.resource.//$packageName/${R.raw.video}")
        videoview.setVideoURI(uri)
        play.setOnClickListener {
            if (!videoview.isPlaying){
                videoview.start()
            }
        }
        pause.setOnClickListener {
            if (videoview.isPlaying){
                videoview.pause()
            }
        }
        //重新播放
        replay.setOnClickListener {
            if (videoview.isPlaying){
                videoview.resume()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        videoview.suspend()
    }
}