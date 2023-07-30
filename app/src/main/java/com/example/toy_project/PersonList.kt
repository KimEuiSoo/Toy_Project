package com.example.toy_project

import com.example.toy_project.Model.PersonModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonList {
    @GET("")
    fun getPersonList(
        @Query("name") name:String,
        @Query("phone") phone:String
    ): Call<List<PersonModel>>
}