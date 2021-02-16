package com.studiomk.randomcatfacts.data.service

import com.studiomk.randomcatfacts.data.model.FoxFact
import com.studiomk.randomcatfacts.data.model.FoxImage
import retrofit2.http.GET

interface FoxService {
    companion object {
        const val RANDOM_FOX_IMAGE_URL = "img/fox"
        const val RANDOM_FOX_FACT_URL = "facts/fox"
    }

    @GET(RANDOM_FOX_IMAGE_URL)
    suspend fun getRandomFoxImage(): FoxImage

    @GET(RANDOM_FOX_FACT_URL)
    suspend fun getRandomFoxFact(): FoxFact
}