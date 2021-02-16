package com.studiomk.randomcatfacts.data.repository

import com.google.gson.GsonBuilder
import com.studiomk.randomcatfacts.data.model.FoxFact
import com.studiomk.randomcatfacts.data.model.FoxImage
import com.studiomk.randomcatfacts.data.service.FoxService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class FoxRepository {
    private val foxApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://some-random-api.ml/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(FoxService::class.java)
    }

    suspend fun getFoxImage(): FoxImage {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            foxApi.getRandomFoxImage()
        } catch (e: Exception) {
            FoxImage(link = "https://i.imgur.com/okV0RkL.jpg")
        }
    }

    suspend fun getFoxFact(): FoxFact {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            foxApi.getRandomFoxFact()
        } catch (e: Exception) {
            FoxFact("An error has occurred, please click Next Fact to try again")
        }
    }
}