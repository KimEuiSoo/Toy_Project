package com.example.toy_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.toy_project.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var register: Register_Fragment
    lateinit var confirm: Confirm_Fragment
    lateinit var bottom: BottomNavigationView

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val personHelper: PersonDB_Test by lazy{
        PersonDB_Test.getInstance(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        register = Register_Fragment()
        confirm = Confirm_Fragment()

        bottom.setOnNavigationItemSelectedListener(this)

        //supportFragmentManager.beginTransaction().replace(R.id.containers, register).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.Register -> {
                supportFragmentManager.beginTransaction().replace(R.id.containers , register).commitAllowingStateLoss()
                return true
            }
            R.id.Confirm -> {
                supportFragmentManager.beginTransaction().replace(R.id.containers, confirm).commitAllowingStateLoss()
                return true
            }
        }

        return false
    }

    /*override fun onDestroy() {
        personHelper.close()
        super.onDestroy()
    }

    private fun showText(text: String){
        binding.tvResult.append(text+"\n\n")
    }


    fun Register_Click(){
        binding.Btn1.setOnClickListener{
            try{
                var document_txt : String = "등록완료"
                if(binding.name.text.toString().trim()=="" || binding.phone.text.toString().trim()=="") {
                   document_txt = "등록실패"
                }
                else{
                    if (Global().Test == 1) {
                        personHelper.insertData(
                            binding.name.text.toString().trim(),
                            binding.phone.text.toString().trim()
                        )
                    } else {
                        val check=NetworkConnect()
                        if(!check)
                            document_txt = "등록실패"
                    }
                }
                reset()
                Toast.makeText(this@MainActivity, document_txt, Toast.LENGTH_SHORT).show()
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

    fun NetworkConnect(): Boolean{
        var check_reg = true
        try{
            var url = URL(Global().register_url)
            val retrofit =  Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            var persondata: PersonData = retrofit.create(PersonData::class.java)
            persondata.requestRegister(binding.name.text.toString().trim(), binding.phone.text.toString().trim()).enqueue(object: Callback<ResponesData>{
                override fun onResponse(call: Call<ResponesData>, response: Response<ResponesData>) {
                }

                override fun onFailure(call: Call<ResponesData>, t: Throwable) {
                    check_reg=false
                }
            })
        }
        catch(e: Exception){
            check_reg=false
        }
        return check_reg
    }*/
}