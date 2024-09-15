package com.ahmed.amdbmoviesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.entities.MoviesResponse
import com.bumptech.glide.Glide

class TrendingMoviesAdapter(var movies: List<MoviesResponse>,var onClick: (Int) -> Unit) : RecyclerView.Adapter<TrendingMoviesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_card, parent, false)
        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = movies[position].title
        holder.rating.text = String.format("%.1f", movies[position].vote_average) + "/10 IMDb"
        Glide.with(holder.card.context).load("https://image.tmdb.org/t/p/w500${movies[position].poster_path}").into(holder.poster)
        holder.itemView.setOnClickListener {
            onClick(movies[position].id)
        }
        Log.d("asd", "onBindViewHolder: ${movies[position].title}")
    }

    override fun getItemCount(): Int = movies.size

    class ViewHolder(val card: View) : RecyclerView.ViewHolder(card){
        var name = card.findViewById<TextView>(R.id.pop_movie_name)
        var rating = card.findViewById<TextView>(R.id.rate)
        var poster = card.findViewById<ImageView>(R.id.pop_movie_poster)
    }

    fun updateData(newMovies: List<MoviesResponse>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}