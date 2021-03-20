package com.ptokaji.doggo.ui.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ptokaji.doggo.R
import com.ptokaji.doggo.util.Result
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DoggoListActivity : AppCompatActivity() {

    lateinit var viewModel: DoggoListViewModel

    lateinit var dogList: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoggoListViewModel::class.java)
        setContentView(R.layout.activity_doggo_list)
        dogList = findViewById(R.id.dog_list)
        progressBar = findViewById(R.id.progress_bar)
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

                }
            }
            progressBar.visibility = View.GONE
            Snackbar
                .make(findViewById(android.R.id.content), R.string.error_text, Snackbar.LENGTH_LONG)
                .show()
        })
    }
}
