package com.example.testloginapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testloginapp.R
import com.example.testloginapp.data.model.Album
import com.example.testloginapp.viewmodels.AlbumDetailViewModel
import com.example.testloginapp.databinding.FragmentAlbumDetailBinding

import android.widget.ImageView
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso

class AlbumDetailFragment : Fragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()
    private val viewModel: AlbumDetailViewModel by viewModels()

    companion object {
        fun newInstance() = AlbumDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val albumId = args.albumId
        viewModel.refreshDataFromNetwork(albumId)

        val imageView = requireView().findViewById<ImageView>(R.id.imageView)
        viewModel.album.observe(viewLifecycleOwner, Observer { album ->
            val imageUrl = album.cover
            Picasso.get()
                .load(imageUrl)
                .into(imageView)
        })
    }
}