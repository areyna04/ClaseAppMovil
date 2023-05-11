package com.example.testloginapp.ui
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testloginapp.R
import com.example.testloginapp.data.model.Album
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testloginapp.viewmodels.AlbumViewModel
import org.json.JSONObject
import android.util.Log

class AddAlbumFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var releaseDateEditText: EditText
    private lateinit var coverEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var recordLabelEditText: EditText
    private lateinit var genreEditText: EditText
    private var viewModel= AlbumViewModel(application = Application())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameEditText = view.findViewById(R.id.nameEditText)
        releaseDateEditText = view.findViewById(R.id.releaseDateEditText)
        descriptionEditText = view.findViewById(R.id.descriptionEditText)
        genreEditText = view.findViewById(R.id.genreEditText)
        recordLabelEditText = view.findViewById(R.id.recordLabelEditText)
        coverEditText = view.findViewById(R.id.coverEditText)
        val submitButton = view.findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val cover = coverEditText.text.toString()
            val releaseDate = releaseDateEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val genre = genreEditText.text.toString()
            val recordLabel = recordLabelEditText.text.toString()

            val postParams = mapOf<String, Any>(
                "name" to name,
                "cover" to cover,
                "releaseDate" to releaseDate,
                "description" to description,
                "genre" to genre,
                "recordLabel" to recordLabel
            )

            viewModel.postDataFromNetwork(JSONObject(postParams))
            Log.d("Tag", JSONObject(postParams).toString())
            findNavController().popBackStack()
        }
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}