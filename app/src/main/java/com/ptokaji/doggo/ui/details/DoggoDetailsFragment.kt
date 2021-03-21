package com.ptokaji.doggo.ui.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.ptokaji.doggo.R
import com.ptokaji.doggo.util.Result
import com.ptokaji.doggo.util.getFormattedBreedName
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoggoDetailsFragment : Fragment(R.layout.fragment_doggo_details) {

    private lateinit var viewModel: DoggoDetailsViewModel
    private val args: DoggoDetailsFragmentArgs by navArgs()

    private lateinit var dogImageList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoggoDetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.doggo_details_title).text =
            args.breed.getFormattedBreedName(args.subBreed)
        dogImageList = view.findViewById(R.id.doggo_details_image_list)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getImages(args.breed, args.subBreed).observe(this, Observer {
            when (it) {
                is Result.Success -> {
                    dogImageList.apply {
                        adapter = DoggoImagesAdapter().apply {
                            dogImagesList = it.data
                        }
                        setHasFixedSize(true)
                    }
                }
                is Result.Error -> {
                    requireView().findViewById<TextView>(R.id.doggo_details_title).text =
                        it.exception.toString()
                }
            }
        })
    }

}