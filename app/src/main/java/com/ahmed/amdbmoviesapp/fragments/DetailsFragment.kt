package com.ahmed.amdbmoviesapp.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.emoji.widget.EmojiTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.datasources.NetworkDSImplementaion
import com.ahmed.amdbmoviesapp.repository.RepositoryImplementation
import com.ahmed.amdbmoviesapp.viewmodels.DetailsViewModel
import com.ahmed.amdbmoviesapp.viewmodlefactories.DetailsViewModelFactory
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {
    private lateinit var mainPoster: ImageView
    private lateinit var posterName: TextView
    private lateinit var genreTv: TextView
    private lateinit var watchBtn: EmojiTextView
    private lateinit var detailsRateIcon: EmojiTextView
    private lateinit var imdbConst: TextView
    private lateinit var detailsRating: TextView
    private lateinit var detailsDurationIcon: EmojiTextView
    private lateinit var durationConst: TextView
    private lateinit var detailsDuration: TextView
    private lateinit var overViewTv: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getInt("movieId")
        val tvId = arguments?.getInt("tvId")

        val viewModelFactory = DetailsViewModelFactory(RepositoryImplementation(NetworkDSImplementaion))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        mainPoster = view.findViewById(R.id.main_poster)
        posterName = view.findViewById(R.id.poster_name)
        genreTv = view.findViewById(R.id.genreTv)
        detailsRating = view.findViewById(R.id.details_rating)
        detailsDuration = view.findViewById(R.id.details_duration)
        overViewTv = view.findViewById(R.id.overViewTv)

        var name: String
        var type: String
        var rate : String
        var overview: String
        var poster: String
        var duration: String

        if (movieId != -1) {
            viewModel.getMovieDetails(movieId!!)
            viewModel.movie.observe(viewLifecycleOwner) {
                name = it.title
                type = "Movie"
                rate = String.format("%.1f", it.vote_average) + "/10"
                overview = it.overview
                poster = "https://image.tmdb.org/t/p/w500${it.poster_path}"
                duration = it.runtime.toString() +" min"

                Glide.with(mainPoster.context).load(poster).into(mainPoster)
                posterName.text = name
                genreTv.text = type
                detailsRating.text = rate
                overViewTv.text = overview
                detailsDuration.text = duration
            }

        } else {
            viewModel.getTvDetails(tvId!!)
            viewModel.tv.observe(viewLifecycleOwner) {
                name = it.name
                type = "TV"
                rate = String.format("%.1f", it.vote_average) + "/10"
                overview = it.overview
                poster = "https://image.tmdb.org/t/p/w500${it.poster_path}"
                duration = it.episode_run_time.toString() + " min"

                Glide.with(mainPoster.context).load(poster).into(mainPoster)
                posterName.text = name
                genreTv.text = type
                detailsRating.text = rate
                overViewTv.text = overview
                detailsDuration.text = duration
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Restore status bar visibility when fragment is destroyed
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}