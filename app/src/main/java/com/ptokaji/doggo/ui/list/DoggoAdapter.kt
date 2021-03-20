package com.ptokaji.doggo.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ptokaji.doggo.R

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
        val title = StringBuilder(breedsUiModel.name.capitalize())
        breedsUiModel.subBreed?.let { subBreed ->
            title.insert(0, subBreed.capitalize())
            title.insert(subBreed.length," ")
        }
        view.findViewById<TextView>(R.id.dog_name).apply {
            text = title.toString()
            setOnClickListener { breedsUiModel.onClickAction(title.toString()) }
        }
    }
}