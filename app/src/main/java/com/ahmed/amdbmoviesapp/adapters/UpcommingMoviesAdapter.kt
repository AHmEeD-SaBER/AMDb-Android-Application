package com.ahmed.amdbmoviesapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.entities.UpcomingMovie
import com.bumptech.glide.Glide

class UpcommingMoviesAdapter(var upMovies: List<UpcomingMovie>,var onClick: (Int) -> Unit): RecyclerView.Adapter<UpcommingMoviesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.popular_movie_card, parent, false)
        return ViewHolder(row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = upMovies[position].title
        holder.rating.text = String.format("%.1f", upMovies[position].vote_average) + "/10 IMDb"
        Glide.with(holder.card.context).load("https://image.tmdb.org/t/p/w500${upMovies[position].poster_path}").into(holder.poster)
        Log.d("asd", "onBindViewHolder: ${upMovies[position].title}")
        holder.itemView.setOnClickListener {
            onClick(upMovies[position].id)
        }
    }

    override fun getItemCount(): Int = upMovies.size

    class ViewHolder(val card: View) : RecyclerView.ViewHolder(card){
        var name = card.findViewById<TextView>(R.id.pop_movie_name)
        var rating = card.findViewById<TextView>(R.id.rate)
        var poster = card.findViewById<ImageView>(R.id.pop_movie_poster)
    }

    fun updateData(newMovies: List<UpcomingMovie>) {
        upMovies = newMovies
        notifyDataSetChanged()
    }
}