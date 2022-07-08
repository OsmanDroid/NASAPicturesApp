package com.osmandroid.nasapicturesapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.osmandroid.nasapicturesapp.R
import com.osmandroid.nasapicturesapp.data.model.NasaItem
import com.osmandroid.nasapicturesapp.databinding.GridItemBinding
import com.osmandroid.nasapicturesapp.ui.adapter.viewholder.GridItemViewHolder
import com.osmandroid.nasapicturesapp.utils.NasaItemDiffUtil

class GridListAdapter(
    private val onClick: (position: Int) -> Unit
) : ListAdapter<NasaItem, GridItemViewHolder>(NasaItemDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return GridItemViewHolder(GridItemBinding.bind(view), onClick)
    }

    override fun onBindViewHolder(holder: GridItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}