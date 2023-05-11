package com.example.testloginapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testloginapp.R
import com.example.testloginapp.databinding.CommentFragmentBinding
import com.example.testloginapp.data.model.Comment
import com.example.testloginapp.ui.adapters.CommentsAdapter
import com.example.testloginapp.viewmodels.CommentViewModel
import org.json.JSONObject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddTrackFragment : Fragment() {
    private var _binding: CommentFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CommentViewModel
    private var viewModelAdapter: CommentsAdapter? = null
    private lateinit var comentario: EditText
    private lateinit var rating: EditText
    private val args: AlbumDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CommentFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CommentsAdapter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.commentsRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        comentario = view.findViewById(R.id.comentario)
        rating = view.findViewById(R.id.rating)
        val albumId = args.albumId


        val submitButton = view.findViewById<Button>(R.id.botonAgregarComentario)
        submitButton.setOnClickListener {
            val comentario = comentario.text.toString()
            val rating = rating.text.toString()
            val id = 1
            val collector = mapOf<String, Any>(
                "id" to id
            )
            val postParams = mapOf<String, Any>(
                "description" to comentario,
                "rating" to rating,
                "collector" to collector
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
        val args: CommentFragmentArgs by this.navArgs()
        Log.d("Args", args.albumId.toString())
        viewModel = ViewModelProvider(this, CommentViewModel.Factory(activity.application, args.albumId)).get(CommentViewModel::class.java)
        viewModel.comments.observe(viewLifecycleOwner, Observer<List<Comment>> {
            it.apply {
                viewModelAdapter!!.comments = this
                if(this.isEmpty()){
                    binding.txtNoComments.visibility = View.VISIBLE
                }else{
                    binding.txtNoComments.visibility = View.GONE
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