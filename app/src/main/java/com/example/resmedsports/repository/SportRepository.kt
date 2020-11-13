package com.example.resmedsports.repository

import com.example.resmedsports.model.SportResultsResponse
import com.example.resmedsports.repository.webservice.SportService
import javax.inject.Inject

class SportRepository @Inject  constructor(private  val sportService: SportService) {
    suspend fun getResults(): SportResultsResponse {
        return sportService.getSportResults()
    }
}