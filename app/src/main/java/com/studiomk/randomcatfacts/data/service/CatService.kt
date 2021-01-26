package com.studiomk.randomcatfacts.data.service

import com.studiomk.randomcatfacts.data.model.CatFact
import com.studiomk.randomcatfacts.data.model.CatImage
import retrofit2.http.GET

interface CatService {

    companion object {
        const val RANDOM_CAT_IMAGE_URL = "v1/images/search"
        const val RANDOM_CAT_FACT_URL = "facts/random"
    }

    @GET(RANDOM_CAT_IMAGE_URL)
    suspend fun getRandomCatImage(): List<CatImage>

    @GET(RANDOM_CAT_FACT_URL)
    suspend fun getRandomCatFact(): CatFact
}