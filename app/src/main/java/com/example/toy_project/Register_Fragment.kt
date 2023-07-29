package com.example.toy_project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.toy_project.Model.ResponesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Register_Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var Btn1: Button

    lateinit var mainActivity: MainActivity

    private val personHelper: PersonDB_Test by lazy{
        PersonDB_Test.getInstance(mainActivity.applicationContext)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onDestroy() {
        personHelper.close()
        super.onDestroy()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        Register_Click()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_register_, container, false)
        name=view.findViewById(R.id.name)
        phone=view.findViewById(R.id.phone)
        Btn1=view.findViewById(R.id.Btn1)
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Register_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun Register_Click(){ Btn1.setOnClickListener{
            try{
                var document_txt : String = "등록완료"
                if(name.text.toString().trim()=="" || phone.text.toString().trim()=="") {
                    document_txt = "등록실패"
                }
                else{
                    if (Global().Test == 1) {
                        personHelper.insertData(
                            name.text.toString().trim(),
                            phone.text.toString().trim()
                        )
                    } else {
                        val check=NetworkConnect()
                        if(!check)
                            document_txt = "등록실패"
                    }
                }
                reset()
                Toast.makeText(mainActivity, document_txt, Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(mainActivity, "등록실패", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun reset(){
        name.setText("")
        phone.setText("")
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
            persondata.requestRegister(name.text.toString().trim(), phone.text.toString().trim()).enqueue(object: Callback<ResponesData>{
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
    }
}