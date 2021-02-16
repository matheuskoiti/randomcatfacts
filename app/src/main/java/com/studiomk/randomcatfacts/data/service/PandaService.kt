package com.studiomk.randomcatfacts.data.service

import com.studiomk.randomcatfacts.data.model.PandaFact
import com.studiomk.randomcatfacts.data.model.PandaImage
import retrofit2.http.GET

interface PandaService {
    companion object {
        const val RANDOM_PANDA_IMAGE_URL = "img/panda"
        const val RANDOM_PANDA_FACT_URL = "facts/panda"
    }

    @GET(RANDOM_PANDA_IMAGE_URL)
    suspend fun getRandomPandaImage(): PandaImage

    @GET(RANDOM_PANDA_FACT_URL)
    suspend fun getRandomPandaFact(): PandaFact
}