package com.ahmed.amdbmoviesapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.amdbmoviesapp.entities.MovieDetailsPage
import com.ahmed.amdbmoviesapp.entities.TvDetailsPage
import com.ahmed.amdbmoviesapp.repository.Repository
import kotlinx.coroutines.launch

class DetailsViewModel(val repository: Repository) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetailsPage>()
    val movie : LiveData<MovieDetailsPage> = _movie
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val response = repository.getMovieDetails(movieId)
            if (response.isSuccessful) {
                _movie.postValue(response.body())
            } else {
                Log.d("DetailsViewModel", "getMovieDetails: ${response.errorBody()}")
            }
        }
    }

    private val _tv = MutableLiveData<TvDetailsPage>()
    val tv : LiveData<TvDetailsPage> = _tv
    fun getTvDetails(tvId: Int) {
        viewModelScope.launch {
            val response = repository.getSeriesDetails(tvId)
            if (response.isSuccessful) {
                _tv.postValue(response.body())
            } else {
                Log.d("DetailsViewModel", "getTvDetails: ${response.errorBody()}")
            }
        }
    }
}