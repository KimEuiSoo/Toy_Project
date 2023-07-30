package com.example.toy_project.Model

import com.google.gson.annotations.SerializedName

data class PersonModel(
    var name : String,
    var msg : String
)

data class ResponesData(
    var code : String,
    var msg : String
)

data class ResponesData_Recieve(
    var page: String,
    var result: List<PersonModel>
)
