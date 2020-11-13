package com.example.resmedsports.model

import com.example.resmedsports.model.base.BaseSportResult
import com.google.gson.annotations.SerializedName
import java.util.*

private const val PUBLICATION_DATE = "publicationDate"
private const val SECONDS = "seconds"
private const val TOURNAMENT = "tournament"
private const val WINNER = "winner"


data class F1SportResult(
    @SerializedName(PUBLICATION_DATE) override val publicationDate: Date,
    @SerializedName(SECONDS) val seconds: Double,
    @SerializedName(TOURNAMENT) val tournament: String,
    @SerializedName(WINNER) val winner: String
): BaseSportResult