package com.example.toy_project

import com.example.toy_project.Global
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.toy_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val personHelper: PersonDB_Test by lazy{
        PersonDB_Test.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Register_Click()
        getAllDb()
    }

    override fun onDestroy() {
        personHelper.close()
        super.onDestroy()
    }

    private fun showText(text: String){
        binding.tvResult.append(text+"\n\n")
    }


    fun Register_Click(){
        binding.Btn1.setOnClickListener{
            try{
                if(Global().Test==1){
                    personHelper.insertData(
                        binding.name.text.toString().trim(),
                        binding.phone.text.toString().trim()
                    )
                }
                else{

                }
                reset()
                Toast.makeText(this@MainActivity, "등록완료", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(this@MainActivity, "등록실패", Toast.LENGTH_LONG).show()
            }
        }


    }

    fun getAllDb() {
        binding.Btn2.setOnClickListener {
            try {
                val selectResult = personHelper.getAllData()
                showText(selectResult)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun reset(){
        with(binding){
            name.setText("")
            phone.setText("")
        }
    }
}