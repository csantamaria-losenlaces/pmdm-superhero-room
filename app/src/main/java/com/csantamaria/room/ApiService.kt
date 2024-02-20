package com.csantamaria.room

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/api/1532094887623548/search/bat")
    suspend fun getSuperheros(): Response<SuperheroDataResponse>

    @GET("/api/1532094887623548/bat")
    suspend fun getSuperheroDetail(): Response<SuperheroDetailResponse>

}