package com.ptokaji.doggo.ui.list

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ptokaji.doggo.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DoggoListActivity : AppCompatActivity() {

    lateinit var viewModel: DoggoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoggoListViewModel::class.java)
        setContentView(R.layout.activity_doggo_list)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDogList().observe(this, Observer { breedsList ->
            findViewById<TextView>(R.id.textView).text = breedsList.toString()
        })
    }
}
