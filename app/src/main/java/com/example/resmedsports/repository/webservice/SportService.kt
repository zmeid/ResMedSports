package com.example.resmedsports.repository.webservice

import com.example.resmedsports.model.SportResultsResponse
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

private const val SPORT_RESULTS_PATH = "results"

interface SportService {
    @POST(SPORT_RESULTS_PATH)
    @Headers("Content-Type: application/json")
    suspend fun getSportResults(): SportResultsResponse
}