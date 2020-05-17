package com.example.app.javaiodemo

import okio.Buffer
import okio.Okio
import okio.sink
import okio.source
import java.io.*

fun main() {
//    writeIo()
    readIo()
//    copy2()
//    OKIoRead()
//    OkIoCopy()
}

fun writeIo() {
    val fops = FileOutputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt")
    val osw = OutputStreamWriter(fops)
    val bufferedWriter = BufferedWriter(osw)
    bufferedWriter.write("aa")
    bufferedWriter.flush()
    bufferedWriter.close()
    osw.close()
    fops.close()
}

fun readIo() {
    val reader = BufferedReader(InputStreamReader(FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt")))
    var line :String?
    while (reader.readLine().also {
        line=it
    }!=null){
    println(line)
    }
    reader.close()
}

fun copy() {
    try {
        BufferedOutputStream(FileOutputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt")).use { outputStream ->
            BufferedInputStream(FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt")).use { inputStream ->
                val datas = ByteArray(1024)
                var line: Int
                while (inputStream.read(datas).also { line = it } != -1) {
                    outputStream.write(datas, 0, line)
                    outputStream.flush()
                }
            }
        }
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

fun copy2() {
    val outputStream = BufferedOutputStream(FileOutputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt"))
    val inputStream = BufferedInputStream(FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt"))
    val datas = ByteArray(1024)
    var line: Int
    while (inputStream.read(datas).also { line = it } != -1) {
        outputStream.write(datas,0,line)
        outputStream.flush()
    }
    outputStream.close()
    inputStream.close()

}


fun OKIoRead(){
    val source = File("D:/GitDir/HenCoderPlus5/Kotlin/text.txt").source()
    val buffer = Buffer()
    source.read(buffer,1024)
    println("读出的数据:${buffer.readUtf8Line()}")
    source.close()
}

fun OkIoCopy(){
    val source = File("D:/GitDir/HenCoderPlus5/Kotlin/text.txt").source()
    val sink = File("D:/GitDir/HenCoderPlus5/Kotlin/copy.txt").sink()
    val buffer = Buffer()
    source.use {source ->
        val lenth = source.read(buffer,1024)
        sink.use { sink->
            sink.write(buffer,lenth)
            sink.flush()
        }

    }
}