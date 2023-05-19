package com.example.testloginapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.testloginapp.R
import com.example.testloginapp.data.model.Performer
import com.example.testloginapp.viewmodels.PerformerDetailViewModel
import com.example.testloginapp.databinding.PerformerDetailFragmentBinding
import androidx.navigation.fragment.findNavController
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso

class PerformerDetailFragment : Fragment() {
    private val args: PerformerDetailFragmentArgs by navArgs()
    private val viewModel: PerformerDetailViewModel by viewModels()

    companion object {
        fun newInstance() = PerformerDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PerformerDetailFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val performerId = args.performerId
        viewModel.refreshDataFromNetwork(performerId)

        val imageView = requireView().findViewById<ImageView>(R.id.imageView)
        viewModel.performer.observe(viewLifecycleOwner, Observer { performer ->
            val imageUrl = performer.image
            Picasso.get()
                .load(imageUrl)
                .into(imageView)
        })
    }
}