package com.example.testloginapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testloginapp.R
import com.example.testloginapp.databinding.PerformerItemBinding
import com.example.testloginapp.data.model.Performer

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.example.testloginapp.ui.PerformerFragmentDirections

class PerformersAdapter : RecyclerView.Adapter<PerformersAdapter.PerformerViewHolder>(){

    var performers :List<Performer> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformerViewHolder {
        val withDataBinding: PerformerItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PerformerViewHolder.LAYOUT,
            parent,
            false)
        return PerformerViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: PerformerViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.performer = performers[position]
        }

        val imageView = holder.viewDataBinding.root.findViewById<ImageView>(R.id.imageView)
        val imageUrl = performers[position].image
        Picasso.get()
            .load(imageUrl)
            .into(imageView)

        holder.viewDataBinding.root.setOnClickListener {
            val action = PerformerFragmentDirections.actionPerformerFragmentToPerformerDetailFragment(performers[position].performerId)
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return performers.size
    }


    class PerformerViewHolder(val viewDataBinding: PerformerItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.performer_item
        }
    }
}