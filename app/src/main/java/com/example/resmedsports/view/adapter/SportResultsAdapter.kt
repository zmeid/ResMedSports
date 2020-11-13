package com.example.resmedsports.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resmedsports.databinding.SportResultsRowBinding

class SportResultsAdapter(private val sportResults: List<String>) :
    RecyclerView.Adapter<SportResultsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: SportResultsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sportResult: String) {
            binding.textViewResultRow.text = sportResult
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SportResultsRowBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return sportResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportResult = sportResults[position]
        holder.bind(sportResult)
    }
}

