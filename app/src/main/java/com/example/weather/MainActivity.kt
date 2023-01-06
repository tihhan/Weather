package com.example.weather


import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.URL


class MainActivity : AppCompatActivity() {

    private var user_field: EditText? = null
    private var main_btn: Button? = null
    private var result_info: TextView? = null


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        user_field = findViewById(R.id.user_field)
        main_btn = findViewById(R.id.main_btn)
        result_info = findViewById(R.id.result_info)

        main_btn?.setOnClickListener {
            if (user_field?.text?.toString()?.trim()?.equals("")!!) {
                Toast.makeText(this, "Enter city", Toast.LENGTH_LONG).show()
            } else {
                val city: String = user_field?.text.toString()
                val key: String = "13588019522b21484c7bef3c1042b0d5"
                val url: String = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric"


                GlobalScope.launch(Dispatchers.Default) {
                    val apiResponse = URL(url).readText()
                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desk = weather.getJSONObject(0).getString("description")

                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")

                    result_info?.text = "Temperature: $temp\n$desk"
                }
            }
        }


    }
}
