package com.ptokaji.doggo.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ptokaji.doggo.R


class DoggoImagesAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    var dogImagesList: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_image_list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dogImagesList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(dogImagesList[position])
    }
}

class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bindView(url: String) {
        val image = view.findViewById<ImageView>(R.id.dog_image)
        image.load(url) {
            crossfade(true)
            placeholder(R.drawable.ic_loading_24)
            error(R.drawable.ic_error_24)
        }
    }
}