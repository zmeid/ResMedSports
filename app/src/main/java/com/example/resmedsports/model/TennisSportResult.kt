package com.example.resmedsports.model

import com.example.resmedsports.model.base.BaseSportResult
import com.google.gson.annotations.SerializedName
import java.util.*

private const val LOOSER = "looser"
private const val PUBLICATION_DATE = "publicationDate"
private const val NUMBER_OF_SETS = "numberOfSets"
private const val TOURNAMENT = "tournament"
private const val WINNER = "winner"

data class TennisSportResult(
    @SerializedName(LOOSER) val looser: String,
    @SerializedName(NUMBER_OF_SETS) val numberOfSets: Int,
    @SerializedName(PUBLICATION_DATE) override val publicationDate: Date,
    @SerializedName(TOURNAMENT) val tournament: String,
    @SerializedName(WINNER) val winner: String
): BaseSportResult