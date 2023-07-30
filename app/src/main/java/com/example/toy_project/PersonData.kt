package com.example.toy_project

import com.example.toy_project.Model.ResponesData
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PersonData {

    @FormUrlEncoded
    @POST("")
    fun requestRegister(
        @Field("name") name:String,
        @Field("phone") phone:String
    ) : Call<ResponesData>
}