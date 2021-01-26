package com.studiomk.randomcatfacts.data.repository

import com.google.gson.GsonBuilder
import com.studiomk.randomcatfacts.data.model.CatImage
import com.studiomk.randomcatfacts.data.service.CatService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatRepository {

    private val theCatApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(CatService::class.java)
    }

    suspend fun getCatImages(): List<CatImage> {
        return theCatApiService.getRandomCatImage()
    }
}