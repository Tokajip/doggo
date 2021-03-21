package com.ptokaji.doggo.ui.details

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ptokaji.doggo.R
import com.ptokaji.doggo.util.getFormattedBreedName

class DoggoDetailsFragment: Fragment(R.layout.fragment_doggo_details) {

    private val args: DoggoDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.doggo_details_title).text = args.breed.getFormattedBreedName(args.subBreed)
    }

}