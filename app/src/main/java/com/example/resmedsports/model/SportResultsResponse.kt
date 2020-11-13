package com.example.resmedsports.model

import com.google.gson.annotations.SerializedName

private const val F1_RESULTS = "f1Results"
private const val NBA_RESULTS = "nbaResults"
private const val TENNIS_RESULTS = "Tennis"

data class SportResultsResponse(
    @SerializedName(F1_RESULTS) val f1Results: List<F1SportResult>,
    @SerializedName(NBA_RESULTS) val nbaResults: List<NbaSportResult>,
    @SerializedName(TENNIS_RESULTS) val tennisResults: List<TennisSportResult>
)