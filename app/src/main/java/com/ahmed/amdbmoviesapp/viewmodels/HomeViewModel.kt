package com.ahmed.amdbmoviesapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.amdbmoviesapp.entities.MoviesResponse
import com.ahmed.amdbmoviesapp.entities.OnAirTv
import com.ahmed.amdbmoviesapp.entities.TvResponse
import com.ahmed.amdbmoviesapp.entities.UpcomingMovie
import com.ahmed.amdbmoviesapp.repository.Repository
import kotlinx.coroutines.launch

class HomeViewModel(val repository: Repository) : ViewModel() {
    private val _pop_movies = MutableLiveData<List<MoviesResponse>>()
    val pop_movies : LiveData<List<MoviesResponse>> = _pop_movies


    private val _pop_tvs = MutableLiveData<List<TvResponse>>()
    val pop_tvs : LiveData<List<TvResponse>> = _pop_tvs

    private val _up_tvs = MutableLiveData<List<OnAirTv>?>()
    val up_tvs : MutableLiveData<List<OnAirTv>?> = _up_tvs

    private val _up_movies = MutableLiveData<List<UpcomingMovie>?>()
    val up_movies : MutableLiveData<List<UpcomingMovie>?> = _up_movies

    fun getPopularMovies() {
        Log.d("asd", "getPopularMovies: called")
        viewModelScope.launch {
            val response = repository.getPopularMovies()
            if (response.isSuccessful) {
                _pop_movies.postValue(response.body()?.results)
            }
            else {
               Log.d("HomeViewModel", "Failed to fetch popular movies")
            }
        }
    }
//
fun getUpcomingMovies() {
    Log.d("asd", "getUpComming: called")
    viewModelScope.launch {
        val response = repository.getUpcomingMovies()
        if (response.isSuccessful) {
            val movieList = response.body()?.results
            Log.d("HomeViewModel", "Movie List: $movieList")  // Log this to check if results is valid
            _up_movies.value = movieList
            Log.d("HomeViewModel", "LiveData Value: ${_up_movies.value}")
        } else {
            Log.d("HomeViewModel", "Failed to fetch upcoming movies")
        }
    }
}


    fun getOnTheAirTvs() {
        Log.d("asd", "getPopularMovies: called")
        viewModelScope.launch {
            val response = repository.getOnAirTvShows()
            if (response.isSuccessful) {
                val tvList = response.body()?.results
                Log.d("HomeViewModel", "Movie List: $tvList")  // Log this to check if results is valid
                _up_tvs.value = tvList
                Log.d("HomeViewModel", "LiveData Value: ${_up_movies.value}")
            }
            else {
                Log.d("HomeViewModel", "Failed to fetch on the air Tvs")
            }
        }
    }

    fun getPopularTv() {
        Log.d("asd", "getPopularMovies: called")
        viewModelScope.launch {
            val response = repository.getPopularSeries()
            if (response.isSuccessful) {
                _pop_tvs.postValue(response.body()?.results)

            }
            else {
                Log.d("HomeViewModel", "Failed to fetch popular Tvs")
            }
        }
    }
}