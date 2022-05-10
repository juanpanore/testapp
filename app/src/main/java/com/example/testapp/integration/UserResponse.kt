package com.example.testapp.integration

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserResponse (
    @SerializedName("id") var id:String,
    @SerializedName("documentType" ) var documentType:String,
    @SerializedName("documentNumber") var documentNumber:String,
    @SerializedName("firstName") var firstName:String,
    @SerializedName("secondName") var secondName:String,
    @SerializedName("lastName") var lastName:String,
    @SerializedName("secondSurname") var secondSurname:String,
    @SerializedName("sex") var sex:String,
    @SerializedName("birthDate") var birthDate:Date,
    @SerializedName("email") var email:String,
    @SerializedName("cellphone") var cellphone:Number,
    @SerializedName("createdDate") var createdDate:String,
    @SerializedName("modifiedDate") var modifiedDate:String
    )

