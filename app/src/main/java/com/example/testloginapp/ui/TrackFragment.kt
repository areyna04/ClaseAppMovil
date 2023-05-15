package com.example.testloginapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testloginapp.R
import com.example.testloginapp.data.model.Track
import com.example.testloginapp.databinding.TrackItemBinding
import com.example.testloginapp.databinding.FragmentTrackBinding
import com.example.testloginapp.ui.adapters.TracksAdapter
import com.example.testloginapp.viewmodels.TrackViewModel

import org.json.JSONObject

class TrackFragment : Fragment() {
    private var _binding: FragmentTrackBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TrackViewModel
    private var viewModelAdapter: TracksAdapter? = null
    private lateinit var name: EditText
    private lateinit var duration: EditText
    private val args: AlbumDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrackBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = TracksAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.tracksRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        name = view.findViewById(R.id.edt_name_track)
        duration = view.findViewById(R.id.edt_dur_track)
        val albumId = args.albumId


        val submitButton = view.findViewById<Button>(R.id.btnAgregarTrack)
        submitButton.setOnClickListener {
            val name = name.text.toString()
            val duration = duration.text.toString()

            val postParams = mapOf<String, Any>(
                "name" to name,
                "duration" to duration
            )

            viewModel.postDataFromNetwork(JSONObject(postParams), albumId)
            Log.d("Tag", JSONObject(postParams).toString())
            findNavController().popBackStack()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_comments)
        val args: TrackFragmentArgs by this.navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, TrackViewModel.Factory(activity.application, args.albumId)).get(
            TrackViewModel::class.java)
        viewModel.tracks.observe(viewLifecycleOwner, Observer<List<Track>> {
            it.apply {
                viewModelAdapter!!.tracks = this
                if(this.isEmpty()){
                    binding.txtNoTracks.visibility = View.VISIBLE
                }else{
                    binding.txtNoTracks.visibility = View.GONE
                }
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}