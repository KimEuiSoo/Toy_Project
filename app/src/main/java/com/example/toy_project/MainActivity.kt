package com.example.toy_project

import com.example.toy_project.Global
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.Btn1)
        val btn2 = findViewById<Button>(R.id.Btn2)
        val name = findViewById<EditText>(R.id.name)
        val phone = findViewById<EditText>(R.id.phone)

        btn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                if(Global().Test==1)
                    Test_Register_Click(name.text.toString(), phone.text.toString())
                else
                    Register_Click(name.text.toString(), phone.text.toString())
            }
        })

        btn2.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                if(Global().Test==1)
                    Test_getAllDb()
                else
                    getAllDb()
            }
        })
    }

    private val personHelper: PersonDB_Test by lazy{
        PersonDB_Test.getInstance(applicationContext)
    }

    fun Test_Register_Click(name: String, phone: String){
        val tv = findViewById<TextView>(R.id.text_view)
        try{
            personHelper.insertData(
                name.toString().trim(),
                phone.toString().trim()
            )
            val selectResult = "이름 ${name.toString()}, 전화번호 ${phone.toString()}"
            tv.append(selectResult + "\n\n")
            Toast.makeText(this@MainActivity, "등록완료 \n$selectResult", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){
            Toast.makeText(this@MainActivity, "등록실패", Toast.LENGTH_LONG).show()
        }
    }

    fun Test_getAllDb() {
        val tv = findViewById<TextView>(R.id.text_view)
        try {
            val selectResult = personHelper.getAllData()
            tv.append(selectResult + "\n\n")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllDb(){

    }

    fun Register_Click(name: String, phone: String){

    }

    fun reset(){
    }
}