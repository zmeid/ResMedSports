package com.example.resmedsports

import com.example.resmedsports.model.SportResultsResponse
import com.example.resmedsports.repository.SportRepository
import com.example.resmedsports.repository.webservice.SportService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * [SportRepository] is not a good candidate to run unit testing, but this is just an example unit test to demonstrate mocking.
 */
@RunWith(MockitoJUnitRunner::class)
class SportRepositoryUnitTest {
    @Mock
    lateinit var sportService: SportService

    @Mock
    lateinit var sportResultsResponse: SportResultsResponse

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        runBlocking {
            Mockito.`when`(sportService.getSportResults()).thenReturn(sportResultsResponse)
        }
    }

    @Test
    fun testGetSportsResults() {
        val sportRepository = SportRepository(sportService)

        runBlocking {
            val sportResultsResponse = sportRepository.getResults()
            Assert.assertEquals(sportResultsResponse, sportResultsResponse)
        }
    }

}