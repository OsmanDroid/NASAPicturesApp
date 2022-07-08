package com.osmandroid.nasapicturesapp.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import com.osmandroid.nasapicturesapp.databinding.GridItemBinding
import com.osmandroid.nasapicturesapp.utils.Extensions.getCircularProgressDrawable

class GridItemViewHolder(
    private val binding: GridItemBinding,
    private val onClick: (view: View, position: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaItem) = with(binding) {
        title.text = item.title
        Glide.with(root.context).load(item.url)
            .placeholder(root.context.getCircularProgressDrawable()).into(imageView)
        root.transitionName = "nasa_image_$adapterPosition"
        root.setOnClickListener {
            onClick(it, adapterPosition)
        }
    }
}