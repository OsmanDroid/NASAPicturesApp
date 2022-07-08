package com.osmandroid.nasapicturesapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.osmandroid.nasapicturesapp.data.model.NasaItem

class NasaItemDiffUtil : DiffUtil.ItemCallback<NasaItem>() {

    override fun areItemsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
        return oldItem.explanation == newItem.explanation
    }

    override fun areContentsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
        return oldItem == newItem
    }

}