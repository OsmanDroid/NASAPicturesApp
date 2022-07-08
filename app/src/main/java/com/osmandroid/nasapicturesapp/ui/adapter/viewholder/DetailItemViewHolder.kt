package com.osmandroid.nasapicturesapp.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import com.osmandroid.nasapicturesapp.databinding.DetailItemBinding
import com.osmandroid.nasapicturesapp.utils.Extensions.getCircularProgressDrawable

class DetailItemViewHolder(
    private val binding: DetailItemBinding,
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaItem) = with(binding) {
        title.text = item.title
        dateTextView.text = item.date
        description.text = item.explanation
        item.copyright?.let {
            copyright.text = it
        }
        Glide.with(root.context).load(item.hdUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(Glide.with(root.context)
                .load(item.url)
                .apply {
                    placeholder(root.context.getCircularProgressDrawable())
                })
            .into(detailImageView)
    }
}