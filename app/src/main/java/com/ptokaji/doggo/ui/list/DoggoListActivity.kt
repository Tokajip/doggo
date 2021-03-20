package com.ptokaji.doggo.ui.list

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ptokaji.doggo.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DoggoListActivity: AppCompatActivity() {

    lateinit var viewModel: DoggoListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoggoListViewModel::class.java)
        setContentView(R.layout.activity_doggo_list)
    }

    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.textView).text = viewModel.getDogList()
    }
}
