package com.luke.codejpmc.ui.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.luke.codejpmc.R
import com.luke.codejpmc.database.DatabaseAlbum
import com.luke.codejpmc.databinding.ListItemBinding

class AlbumsAdapter(private val onItemClicked:(DatabaseAlbum) ->Unit ) : RecyclerView.Adapter<AlbumsViewHolder>() {

    var result: List<DatabaseAlbum> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val withDataBinding: ListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumsViewHolder.LAYOUT,
            parent,
            false
        )
        return AlbumsViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.viewDataBinding.also{
            it.result = result[position]
        }

        holder.itemView.setOnClickListener {
            onItemClicked( result[position])
        }
    }

    override fun getItemCount() = result.size
}

class AlbumsViewHolder(val viewDataBinding: ListItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.list_item
    }
}
