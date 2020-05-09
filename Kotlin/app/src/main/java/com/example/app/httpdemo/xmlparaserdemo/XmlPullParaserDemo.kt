package com.example.app.httpdemo.xmlparaserdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import kotlinx.android.synthetic.main.activity_test.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.lang.StringBuilder
import kotlin.concurrent.thread

class XmlPullParaserDemo :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

    }

    private fun sendOkhttp(){
//        val response = StringBuilder()
        thread {
            val client = OkHttpClient()
            val request = Request.Builder().url("https://www.baidu.com").build()
            val res = client.newCall(request).execute()
//            showResponse( res.body?.string())
            xmlParsaser(res.body?.string())
        }

    }

    private fun xmlParsaser(res :String?){
        val factory = XmlPullParserFactory.newInstance()
        val xmlPullParser = factory.newPullParser()
        xmlPullParser.setInput(StringReader(res))
       var id  = ""
       var name  = ""
       var version  = ""
        var evetType = xmlPullParser.eventType
        var nodename = xmlPullParser.name
        while (evetType!=XmlPullParser.END_DOCUMENT){
            when(evetType){
                XmlPullParser.START_TAG->{
                    when(nodename){
                        "id" -> id = xmlPullParser.nextText()
                        "name" -> name = xmlPullParser.nextText()
                        "version" -> version = xmlPullParser.nextText()
                    }

                }
                XmlPullParser.END_TAG->{
                    if ("app"==nodename){
                        Log.i("Don", "xmlParsaser: id:$id")
                        Log.i("Don", "xmlParsaser: name:$name")
                        Log.i("Don", "xmlParsaser: version:$version")
                    }
                }
            }
            evetType = xmlPullParser.next()
        }
    }


    private fun showResponse(response: String?) {
        runOnUiThread {
            Log.i("Don", "sendHttpUrlConnetion: ${response.toString()}")
            text.text = response
        }
    }
}