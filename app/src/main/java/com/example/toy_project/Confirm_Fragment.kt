package com.example.toy_project

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Confirm_Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var tvResult: TextView

    private val personHelper: PersonDB_Test by lazy{
        PersonDB_Test.getInstance(mainActivity.applicationContext)
    }

    override fun onDestroy() {
        personHelper.close()
        super.onDestroy()
    }

    private fun showText(text: String){
        tvResult.append(text+"\n\n")
    }

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        getAllDb()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var c_view : View = inflater.inflate(R.layout.fragment_confirm_, container, false)
        tvResult = c_view.findViewById(R.id.tvResult)
        // Inflate the layout for this fragment
        return c_view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Confirm_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun getAllDb() {
        try {
            val selectResult = personHelper.getAllData()
            showText(selectResult)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}