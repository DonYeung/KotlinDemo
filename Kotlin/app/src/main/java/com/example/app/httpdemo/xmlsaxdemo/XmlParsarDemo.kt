package com.example.app.httpdemo.xmlsaxdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.InputSource
import org.xml.sax.helpers.XMLReaderFactory
import java.io.StringReader
import java.lang.Exception
import javax.xml.parsers.SAXParserFactory
import kotlin.concurrent.thread

class XmlParsarDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textcenter)

        sendOkhttp()
    }

    private fun sendOkhttp() {
//        val response = StringBuilder()
        thread {
            val client = OkHttpClient()
            val request = Request.Builder().url("https://www.baidu.com").build()
            val res = client.newCall(request).execute()
//            showResponse(res.body?.string())
            parsarXmlSax(res.body?.string())
        }

    }

    private fun parsarXmlSax(res: String?) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = XmlSaxParserHandler()
            xmlReader.contentHandler = handler
            xmlReader.parse(InputSource(StringReader(res)))
        }catch (e :Exception){
            e.printStackTrace()
        }
    }
}