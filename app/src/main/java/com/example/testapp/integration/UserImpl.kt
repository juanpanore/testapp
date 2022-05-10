package com.example.testapp.integration

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class UserImpl () {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://aqueous-earth-47838.herokuapp.com/api/v1/rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun searchUser(documentType: String, documentNumber: String): UserResponse {
        val call =
            getRetrofit().create(APIService::class.java).getClient("client?documentType=$documentType&documentNumber=$documentNumber")
                .execute()
        println("raw "+ call.raw())
        println("header "+ call.headers())
        println("message "+call.message())
        println("body " + call.body())
        return call.body() as UserResponse


    }

}