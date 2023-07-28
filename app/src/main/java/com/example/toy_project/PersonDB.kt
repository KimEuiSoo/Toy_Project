package com.example.toy_project

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class PersonDB: Thread() {
    override fun run(){
        var url = URL(Global().register_url)
        var conn = url.openConnection()
        var input = conn.getInputStream()
        var isr = InputStreamReader(input)

        var br = BufferedReader(isr)

        var str : String? = null
        var buf = StringBuffer()

        do{
            str = br.readLine()

            if(str!=null){
                buf.append(str)
            }
        }while(str!=null)

        var root = JSONObject(buf.toString())
    }
}