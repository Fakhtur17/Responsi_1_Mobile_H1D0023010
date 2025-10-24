package com.example.responsi1mobileh1d023010.data.network

import com.example.responsi1mobileh1d023010.data.model.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface FootballDataApi {
    @Headers("X-Auth-Token: ${com.example.responsi1mobileh1d023010.utils.Constants.API_TOKEN}")
    @GET("teams/{id}")
    suspend fun getTeam(
        @Path("id") id: Int
    ): Response<TeamResponse>
}