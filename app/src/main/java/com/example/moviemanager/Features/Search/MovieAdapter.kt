package com.example.moviemanager.Features.Search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemanager.R
import com.example.moviemanager.databinding.MovieItemBinding
import com.example.moviemanager.Model.Search
import com.example.moviemanager.loadUrl

class MovieAdapter(val clickListener: (String) -> Unit) :
    ListAdapter<Search, MovieAdapter.SearchMovieVH>(MovieDiffUtils()) {


    inner class SearchMovieVH(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Search) {
            binding.tvMovieTitle.text = item.Title
            binding.tvYear.text = item.Year
            binding.ivPoster.loadUrl(item.Poster)

            binding.root.setOnClickListener {
                clickListener(item.imdbID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieVH {
        val binding = MovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return SearchMovieVH(binding)
    }

    override fun onBindViewHolder(holder: SearchMovieVH, position: Int) {

        return holder.onBind(getItem(position))
    }

}

class MovieDiffUtils : DiffUtil.ItemCallback<Search>() {
    override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
        return oldItem.Title == newItem.Title
                && oldItem.Poster == newItem.Poster
                && oldItem.Year == newItem.Year
    }

}
