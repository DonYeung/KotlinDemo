package com.example.app.mediaplayerdemo

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_textcenter.*

class MediaPlayerActivity :AppCompatActivity() {
    private val mediaplayer =MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textcenter)
        initMediaplayer()
        btn1.setOnClickListener {
            //开始播放
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
            }
            //暂停播放
            if (mediaplayer.isPlaying){
                mediaplayer.pause()
            }
            //停止播放
            if (mediaplayer.isPlaying){
                mediaplayer.reset()
                initMediaplayer()
            }
        }
    }
    private fun initMediaplayer(){
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mediaplayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaplayer.prepare()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaplayer.stop()
        mediaplayer.release()
    }
}