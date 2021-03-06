package com.example.resmedsports.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resmedsports.model.F1SportResult
import com.example.resmedsports.model.NbaSportResult
import com.example.resmedsports.model.SportResultsResponse
import com.example.resmedsports.model.TennisSportResult
import com.example.resmedsports.model.base.BaseSportResult
import com.example.resmedsports.repository.SportRepository
import com.example.resmedsports.util.ApiResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val sportRepository: SportRepository
) : ViewModel() {

    private val simpleDateFormat: DateFormat = SimpleDateFormat.getDateInstance()

    private val sportResultsMutable: MutableLiveData<ApiResponseWrapper<List<String>>> =
        MutableLiveData()

    val sportResultsLiveData: LiveData<ApiResponseWrapper<List<String>>> =
        sportResultsMutable

    private val mostRecentDateMutable: MutableLiveData<String> =
        MutableLiveData()

    val mostRecentDateLiveData: LiveData<String> =
        mostRecentDateMutable

    /**
     * Gets sport results from [SportRepository],
     * converts them to readable list of strings and posts it to UI with [sportResultsLiveData].
     */
    fun getSportResults() {
        viewModelScope.launch(Dispatchers.IO) {
            sportResultsMutable.postValue(ApiResponseWrapper.loading())
            try {
                val sportResultsResponse = sportRepository.getResults()

                sportResultsMutable.postValue(
                    ApiResponseWrapper.success(toStringListOfMostRecentResults(sportResultsResponse))
                )
            } catch (e: Exception) {
                sportResultsMutable.postValue(ApiResponseWrapper.error(exception = e))
                mostRecentDateMutable.postValue("")
                Timber.e(e)
            }
        }
    }

    /**
     * Gets the most recent date sport results with [getMostRecentDateSportResults],
     * converts results to readable strings with [convertResultToStrings]
     */
    private fun toStringListOfMostRecentResults(sportResultsResponse: SportResultsResponse): List<String> {
        val mostRecentDateSportResults = getMostRecentDateSportResults(sportResultsResponse)

        return convertResultToStrings(mostRecentDateSportResults)
    }

    /**
     * Flattens all type of sport results,
     * sorts them by date descending,
     * filters the list to most recent dates.
     */
    private fun getMostRecentDateSportResults(sportResultsResponse: SportResultsResponse): List<BaseSportResult> {
        var sportResults: List<BaseSportResult> = listOf(
            sportResultsResponse.f1Results,
            sportResultsResponse.nbaResults,
            sportResultsResponse.tennisResults
        ).flatten()

        sportResults = sportResults.sortedByDescending { it.publicationDate }

        val mostRecentDate = sportResults[0].publicationDate

        mostRecentDateMutable.postValue(simpleDateFormat.format(mostRecentDate))

        return sportResults.filter {
            isSameDay(
                mostRecentDate,
                it.publicationDate,
                simpleDateFormat
            )
        }
    }

    /**
     * Converts sport results to list of strings which are readable by user.
     */
    private fun convertResultToStrings(mostRecentDateSportResults: List<BaseSportResult>): List<String> {
        val stringResults = mutableListOf<String>()
        mostRecentDateSportResults.forEach {
            val stringResult: String = when (it) {
                is F1SportResult -> {
                    f1ResultToString(it)
                }
                is NbaSportResult -> {
                    nbaResultToString(it)
                }
                is TennisSportResult -> {
                    tennisResultToString(it)
                }
                else -> "Sport result type not supported"
            }
            stringResults.add(stringResult)
        }
        return stringResults
    }

    private fun f1ResultToString(f1SportResult: F1SportResult): String {
        f1SportResult.apply {
            return "$winner wins $tournament by $seconds seconds"
        }
    }

    private fun nbaResultToString(nbaSportResult: NbaSportResult): String {
        nbaSportResult.apply {
            return "$mvp leads $winner to game $gameNumber win in the $tournament"
        }
    }

    private fun tennisResultToString(tennisSportResult: TennisSportResult): String {
        tennisSportResult.apply {
            return "$tournament: $winner wins against $looser in $numberOfSets sets"
        }
    }

    private fun isSameDay(date1: Date, date2: Date, dateFormat: DateFormat): Boolean {
        return dateFormat.format(date1) == dateFormat.format(date2)
    }
}