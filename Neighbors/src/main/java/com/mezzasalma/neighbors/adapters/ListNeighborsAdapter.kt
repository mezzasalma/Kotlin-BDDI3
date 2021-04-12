package com.mezzasalma.neighbors.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mezzasalma.neighbors.R
import com.mezzasalma.neighbors.databinding.NeighborItemBinding
import com.mezzasalma.neighbors.models.Neighbor

class ListNeighborsAdapter(private val items: List<Neighbor>, private val handler: ListNeighborHandler) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: NeighborItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.neighbor_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbor: Neighbor = items[position]
        holder.binding.neighbor = neighbor

        holder.binding.itemListDeleteButton.setOnClickListener() {
            handler.onDeleteNeighbor(neighbor)
        }

        holder.binding.itemListLikeButton.setOnClickListener() {
            handler.onUpdateFavoriteStatus(neighbor)
        }

        holder.binding.itemListLinkButton.setOnClickListener() {
            handler.openLink(neighbor)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: NeighborItemBinding) : RecyclerView.ViewHolder(binding.root)
}
