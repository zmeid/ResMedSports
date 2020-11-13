package com.example.resmedsports.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.resmedsports.R
import com.example.resmedsports.databinding.ActivityMainBinding
import com.example.resmedsports.util.ApiResponseWrapper
import com.example.resmedsports.view.adapter.SportResultsAdapter
import com.example.resmedsports.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var sportResultsAdapter: SportResultsAdapter
    private val sportResults = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainActivityViewModel::class.java)

        setupViews()

        setObservers()
    }

    private fun setupViews() {
        sportResultsAdapter = SportResultsAdapter(sportResults)

        binding.apply {

            buttonGetResults.setOnClickListener {
                mainActivityViewModel.getSportResults()
            }

            recyclerViewSportResults.adapter = sportResultsAdapter

            swipeRefreshLayout.setOnRefreshListener(this@MainActivity)
        }
    }

    private fun setObservers() {
        observeSportResults()
        observeMostRecentDate()
    }

    private fun observeSportResults() {
        mainActivityViewModel.sportResultsLiveData.observe(this, Observer {
            handleSportResults(it)
        })
    }

    private fun observeMostRecentDate() {
        mainActivityViewModel.mostRecentDateLiveData.observe(this, Observer {
            if (it.isNullOrBlank()) {
                setAppBarTitle(getString(R.string.app_name))
            } else {
                setAppBarTitle(getString(R.string.results_for, it))
            }
        })
    }

    private fun handleSportResults(apiResponseWrapper: ApiResponseWrapper<List<String>>) {
        when (apiResponseWrapper.status) {
            ApiResponseWrapper.Status.LOADING -> {
                setActivityLoading()
            }
            ApiResponseWrapper.Status.SUCCESS -> {

                if (apiResponseWrapper.data.isNullOrEmpty()) {
                    setActivityError(getString(R.string.no_result_found))
                } else {
                    setActivitySuccess(apiResponseWrapper.data)
                }
            }
            ApiResponseWrapper.Status.ERROR -> {
                setActivityError(getString(R.string.general_error_message))
            }
        }
    }

    private fun showErrorText(error: String) {
        binding.textViewError.text = error
        binding.textViewError.visibility = View.VISIBLE
    }

    private fun hideErrorText() {
        binding.textViewError.visibility = View.GONE
    }

    private fun showGetResultButton() {
        binding.buttonGetResults.visibility = View.VISIBLE
    }

    private fun hideGetResultButton() {
        binding.buttonGetResults.visibility = View.GONE
    }

    private fun refreshRecyclerViewAdapter(resultsData: List<String>) {
        sportResults.clear()
        sportResults.addAll(resultsData)
        sportResultsAdapter.notifyDataSetChanged()
    }

    private fun setActivityLoading() {
        refreshRecyclerViewAdapter(emptyList())
        showProgressBar(binding.progressBar)
        hideErrorText()
        hideGetResultButton()
        setAppBarTitle(getString(R.string.loading))
    }

    private fun setActivitySuccess(resultsData: List<String>) {
        hideProgressBar(binding.progressBar)
        hideErrorText()
        hideGetResultButton()
        refreshRecyclerViewAdapter(resultsData)
    }

    private fun setActivityError(errorMessage: String) {
        hideProgressBar(binding.progressBar)
        showErrorText(errorMessage)
        showGetResultButton()
        refreshRecyclerViewAdapter(emptyList())
        setAppBarTitle(getString(R.string.app_name))
    }

    override fun onRefresh() {
        mainActivityViewModel.getSportResults()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    private fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }
}