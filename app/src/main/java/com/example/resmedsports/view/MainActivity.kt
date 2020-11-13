package com.example.resmedsports.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.resmedsports.R
import com.example.resmedsports.databinding.ActivityMainBinding
import com.example.resmedsports.util.ApiResponseWrapper
import com.example.resmedsports.viewmodel.MainActivityViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mainActivityViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MainActivityViewModel::class.java)


        binding.buttonGetResults.setOnClickListener {
            mainActivityViewModel.getSportResults()
        }

        observeSportResults()
    }

    private fun observeSportResults() {
        mainActivityViewModel.sportResultsLiveData.observe(this, Observer {
            handleSportResults(it)
        })
    }

    private fun handleSportResults(apiResponseWrapper: ApiResponseWrapper<List<String>>) {
        when (apiResponseWrapper.status) {
            ApiResponseWrapper.Status.LOADING -> {
                showProgressBar(binding.progressBar)
                hideErrorText()
                hideGetResultButton()
            }
            ApiResponseWrapper.Status.SUCCESS -> {
                hideProgressBar(binding.progressBar)
                hideErrorText()
                hideGetResultButton()
            }
            ApiResponseWrapper.Status.ERROR -> {
                hideProgressBar(binding.progressBar)
                showErrorText(getString(R.string.general_error_message))
                showGetResultButton()
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
}