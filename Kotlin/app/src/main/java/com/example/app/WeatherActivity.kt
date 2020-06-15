package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app.coroutinedemo.http.Api
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
         initData()
    }
    private fun initData(){
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)

        lifecycleScope.launch(Dispatchers.Main){
            withContext(Dispatchers.Main){
                val response = api.listHourData()
                if (response.status.equals("ok")){
                    scrollChartView.setData(response.result.hourly.temperature)
                }
            }
        }
    }
}