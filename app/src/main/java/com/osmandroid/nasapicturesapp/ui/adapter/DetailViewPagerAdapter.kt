package com.osmandroid.nasapicturesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.osmandroid.nasapicturesapp.R
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import com.osmandroid.nasapicturesapp.databinding.DetailItemBinding
import com.osmandroid.nasapicturesapp.ui.adapter.viewholder.DetailItemViewHolder
import com.osmandroid.nasapicturesapp.utils.NasaItemDiffUtil

class DetailViewPagerAdapter(
    private val onImageLoaded: () -> Unit
) :
    ListAdapter<NasaItem, DetailItemViewHolder>(NasaItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_item, parent, false)
        return DetailItemViewHolder(DetailItemBinding.bind(view), onImageLoaded)
    }

    override fun onBindViewHolder(holder: DetailItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}