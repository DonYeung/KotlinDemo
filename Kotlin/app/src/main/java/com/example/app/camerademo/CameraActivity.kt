package com.example.app.camerademo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.app.R
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_textcenter.*
import java.io.File

class CameraActivity :AppCompatActivity() {
    val takePhoto = 1
    lateinit var imageUri :Uri
    lateinit var outputImage :File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_textcenter)

        btn1.setOnClickListener {
            outputImage = File(externalCacheDir,"output_image.png")
            if (outputImage.exists()){
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this,"com.example.hencoder.fileprovider",outputImage)

            }else{
                Uri.fromFile(outputImage)
            }
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,takePhoto)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            takePhoto-> {
                if(resultCode== Activity.RESULT_OK){
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    iv1.setImageBitmap(rotateIfRequired(bitmap))
                }
            }
        }
    }

    fun rotateIfRequired(bitmap: Bitmap):Bitmap{
        val exif = ExifInterface(outputImage.path)
        var orienntation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL)
        return when(orienntation){
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap,90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap,180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap,270)
            else ->bitmap
        }
    }
    fun rotateBitmap(bitmap:Bitmap,degree:Int):Bitmap{
        val matrix =Matrix()
        matrix.postRotate(degree.toFloat())
        val rotateBitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)
        bitmap.recycle()
        return rotateBitmap
    }
}