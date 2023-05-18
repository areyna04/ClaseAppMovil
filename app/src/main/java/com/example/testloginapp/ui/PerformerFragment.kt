package com.example.testloginapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testloginapp.R
import com.example.testloginapp.data.model.Performer
import com.example.testloginapp.databinding.PerformerFragmentBinding
import com.example.testloginapp.ui.adapters.PerformersAdapter
import com.example.testloginapp.viewmodels.PerformerViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PerformerFragment : Fragment() {
    private var _binding: PerformerFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PerformerViewModel
    private var viewModelAdapter: PerformersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PerformerFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = PerformersAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.performersRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_performer)
        viewModel = ViewModelProvider(this, PerformerViewModel.Factory(activity.application)).get(PerformerViewModel::class.java)

        viewModel.performers.observe(viewLifecycleOwner, Observer<List<Performer>> {
            it.apply {
                viewModelAdapter!!.performers = this
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