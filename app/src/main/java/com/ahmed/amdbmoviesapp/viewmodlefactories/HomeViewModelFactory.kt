package com.ahmed.amdbmoviesapp.viewmodlefactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmed.amdbmoviesapp.repository.Repository
import com.ahmed.amdbmoviesapp.viewmodels.HomeViewModel
import java.lang.IllegalArgumentException

class HomeViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(repository) as T
        } else {
            throw IllegalArgumentException("HomeViewModel class not found")
        }
    }

}