package com.ptokaji.doggo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ptokaji.doggo.R
import com.ptokaji.doggo.util.getFormattedBreedName

class DoggoAdapter : RecyclerView.Adapter<DoggoViewHolderItem>() {

    var dogList: List<BreedsUiModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoggoViewHolderItem {
        return DoggoViewHolderItem(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_doggo_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: DoggoViewHolderItem, position: Int) {
        holder.bindView(dogList[position])
    }
}

class DoggoViewHolderItem(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindView(breedsUiModel: BreedsUiModel) {

        view.findViewById<TextView>(R.id.dog_name).apply {
            text = breedsUiModel.name.getFormattedBreedName(breedsUiModel.subBreed)
            setOnClickListener {
                findNavController().navigate(
                    DoggoListFragmentDirections.actionDoggoListFragmentToDoggoDetailsFragment(
                        breedsUiModel.name, breedsUiModel.subBreed
                    )
                )
            }
        }
    }
}