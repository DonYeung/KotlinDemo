package com.example.app.httpdemo.xmlsaxdemo

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import kotlin.text.StringBuilder

class XmlSaxParserHandler : DefaultHandler() {
    var nodeName = ""
    private lateinit var id :StringBuilder
    private lateinit var name :StringBuilder
    private lateinit var version :StringBuilder
    override fun startDocument() {
        super.startDocument()
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
        super.startElement(uri, localName, qName, attributes)
        nodeName = localName
        Log.i("Don", "startElement: uri,${uri}")
        Log.i("Don", "startElement: localName,${localName}")
        Log.i("Don", "startElement: qName,${qName}")

    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        when(nodeName){
            "id" -> id.append(ch,start,length)
            "name" -> name.append(ch,start,length)
            "version" -> version.append(ch,start,length)
        }

    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        if ("app"==localName){
            Log.i("Don", "xmlParsaser: id:${id.trim()}")
            Log.i("Don", "xmlParsaser: name:${name.trim()}")
            Log.i("Don", "xmlParsaser: version:${version.trim()}")
        }
    }

    override fun endDocument() {
        super.endDocument()
    }


}