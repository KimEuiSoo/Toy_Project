package com.example.toy_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name_text = findViewById<EditText>(R.id.buttontext1);
        val phone_text = findViewById<EditText>(R.id.buttontext2);

        name_text.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?){
                name_text.setText("");
            }
        })
    }
}