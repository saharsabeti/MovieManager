package com.example.moviemanager.Features.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.moviemanager.R
import com.example.moviemanager.Base.AppDatabase
import com.example.moviemanager.databinding.FragmentMovieDetailBinding
import com.example.moviemanager.loadUrl
import com.example.moviemanager.Model.FavoriteMovies
import com.example.moviemanager.Model.MovieDetails
import com.example.moviemanager.Repository.RetroInterface
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailBinding

    private val viewModel by viewModels<MovieDetailViewModel>()

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var retroInterface: RetroInterface

    val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.searchMovieById(args.omdbId)
        viewModel.liveData.observe(viewLifecycleOwner) {
            loadMovieInfoIntoView(it)

            lifecycleScope.launch(Dispatchers.Main) {
                if (viewModel.isSavedMovie(it.imdbID)) {
                    binding.addBtn.text = getString(R.string.btn_delete)
                } else {
                    binding.addBtn.text = getString(R.string.add_to_fav)
                }
            }
        }

        binding.addBtn.setOnClickListener {
            viewModel.liveData.observe(viewLifecycleOwner) {
                lifecycleScope.launch(Dispatchers.Main) {
                    val favMovieInfo = FavoriteMovies(
                        it.imdbID, it.Title, it.Actors, it.Plot,
                        it.Year, it.Awards, it.Country, it.Director, it.Genre,
                        it.Language, it.Metascore, it.Poster, it.Rated, it.Released,
                        it.Runtime, it.Type, it.Writer, it.imdbRating, it.imdbVotes, it.totalSeasons
                    )

                    if (viewModel.isSavedMovie(it.imdbID)) {
                        viewModel.unsaveMovie(favMovieInfo)
                        binding.addBtn.text = getString(R.string.add_to_fav)
                    } else {
                        binding.addBtn.text = getString(R.string.btn_delete)
                        viewModel.saveMovie(favMovieInfo)
                    }
                }
            }
        }
    }

    private fun loadMovieInfoIntoView(movieDetails: MovieDetails) {
        binding.titleTv.text = movieDetails.Title
        binding.actorsTv.text = movieDetails.Actors
        binding.plotTv2.text = movieDetails.Plot
        binding.posterImg.loadUrl(movieDetails.Poster)
    }
}