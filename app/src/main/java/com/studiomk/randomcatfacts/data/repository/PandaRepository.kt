package com.studiomk.randomcatfacts.data.repository

import com.google.gson.GsonBuilder
import com.studiomk.randomcatfacts.data.model.PandaFact
import com.studiomk.randomcatfacts.data.model.PandaImage
import com.studiomk.randomcatfacts.data.service.PandaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class PandaRepository {
    private val pandaApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://some-random-api.ml/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(PandaService::class.java)
    }

    suspend fun getPandaImage(): PandaImage {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            pandaApi.getRandomPandaImage()
        } catch (e: Exception) {
            PandaImage(link = "https://i.imgur.com/u1cb7a7.jpg")
        }
    }

    suspend fun getPandaFact(): PandaFact {
        /**
         * Instead of a simple try/catch, I could create a wrapper class to handle and return
         * success, generic or specific error
         */
        return try {
            pandaApi.getRandomPandaFact()
        } catch (e: Exception) {
            PandaFact("An error has occurred, please click Next Fact to try again")
        }
    }
}