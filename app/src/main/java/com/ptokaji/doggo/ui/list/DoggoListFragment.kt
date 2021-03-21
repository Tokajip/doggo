package com.ptokaji.doggo.ui.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ptokaji.doggo.R
import com.ptokaji.doggo.util.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoggoListFragment : Fragment(R.layout.fragment_doggo_list) {

    private lateinit var viewModel: DoggoListViewModel

    private lateinit var dogList: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoggoListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dogList = view.findViewById(R.id.dog_list)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDogList().observe(this, Observer { breedsListResult ->
            when (breedsListResult) {
                is Result.Success -> {
                    dogList.apply {
                        adapter = DoggoAdapter().apply {
                            dogList = breedsListResult.data
                        }
                        setHasFixedSize(true)
                        visibility = View.VISIBLE
                    }
                }
                is Result.Error -> {
                    Snackbar
                        .make(
                            this.requireView().findViewById(android.R.id.content),
                            R.string.error_text,
                            Snackbar.LENGTH_LONG
                        )
                        .show()
                }
            }
            progressBar.visibility = View.GONE
        })
    }
}