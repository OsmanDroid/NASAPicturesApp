package com.osmandroid.nasapicturesapp.ui.adapter.viewholder

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import com.osmandroid.nasapicturesapp.databinding.DetailItemBinding
import com.osmandroid.nasapicturesapp.utils.Extensions.getAbbreviatedDate
import com.osmandroid.nasapicturesapp.utils.Extensions.getCircularProgressDrawable

class DetailItemViewHolder(
    private val binding: DetailItemBinding,
    private val onImageLoaded: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaItem) = with(binding) {
        title.text = item.title
        dateTextView.text = item.date.getAbbreviatedDate()
        description.text = item.explanation
        item.copyright?.let {
            copyright.text = it
        }
        Glide.with(root.context).load(item.hdUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(Glide.with(root.context)
                .load(item.url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        onImageLoaded()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        onImageLoaded()
                        return false
                    }
                })
                .apply {
                    placeholder(root.context.getCircularProgressDrawable())
                })
            .into(detailImageView)
        detailImageView.transitionName = "nasa_image_$adapterPosition"
    }
}