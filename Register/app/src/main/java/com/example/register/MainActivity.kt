package com.example.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.register.Model.ResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var btn1 : Button
    lateinit var edit : EditText
    var url_post:String = "https://api.odcloud.kr/api/nts-businessman/v1/"
    var TAG = "tst5"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.Btn1)
        edit = findViewById(R.id.register)

        btn_click()
    }

    fun btn_click(){
        btn1.setOnClickListener{
            try{
                var url = URL(url_post)
                val retrofit =  Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                var persondata: RegisterData = retrofit.create(RegisterData::class.java)
                persondata.requestRegister(edit.text.toString().trim()).enqueue(object:
                    Callback<ResponseModel> {
                    override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                        System.out.println(response.body())
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        System.out.println("")
                    }
                })
            }
            catch (e:Exception){
                Toast.makeText(this@MainActivity, "등록실패", Toast.LENGTH_LONG).show()
            }
        }
    }
}