package com.example.testloginapp.ui.adapters

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.testloginapp.R
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.databinding.AlbumItemBinding

class  AlbumsAdapter  : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    var albums :List<Album> = emptyList()
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumsAdapter.AlbumViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AlbumsAdapter.AlbumViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album  = albums[position]
        }
        holder.viewDataBinding.root.setOnClickListener {
           // val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
            // Navigate using that action
           // holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return albums.size
    }


    class AlbumViewHolder(val viewDataBinding: AlbumItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_item
        }
    }

}