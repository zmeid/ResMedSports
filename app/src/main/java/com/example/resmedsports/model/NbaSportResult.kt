package com.example.resmedsports.model

import com.example.resmedsports.model.base.BaseSportResult
import com.google.gson.annotations.SerializedName
import java.util.*

private const val GAME_NUMBER = "gameNumber"
private const val LOOSER = "looser"
private const val MVP = "mvp"
private const val PUBLICATION_DATE = "publicationDate"
private const val TOURNAMENT = "tournament"
private const val WINNER = "winner"

data class NbaSportResult(
    @SerializedName(GAME_NUMBER) val gameNumber: Int,
    @SerializedName(LOOSER) val looser: String,
    @SerializedName(MVP) val mvp: String,
    @SerializedName(PUBLICATION_DATE) override val publicationDate: Date,
    @SerializedName(TOURNAMENT) val tournament: String,
    @SerializedName(WINNER) val winner: String
): BaseSportResult