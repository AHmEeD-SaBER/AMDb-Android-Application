package com.ahmed.amdbmoviesapp.fragments

import SpaceItemDecoration
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.amdbmoviesapp.R
import com.ahmed.amdbmoviesapp.adapters.PopularTvAdapter
import com.ahmed.amdbmoviesapp.adapters.TrendingMoviesAdapter
import com.ahmed.amdbmoviesapp.adapters.UpcommingMoviesAdapter
import com.ahmed.amdbmoviesapp.adapters.UpcommingTvsAdapter
import com.ahmed.amdbmoviesapp.datasources.NetworkDSImplementaion
import com.ahmed.amdbmoviesapp.repository.RepositoryImplementation
import com.ahmed.amdbmoviesapp.viewmodels.HomeViewModel
import com.ahmed.amdbmoviesapp.viewmodlefactories.HomeViewModelFactory

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var greetings : TextView
    private lateinit var pop_movies_adapter: TrendingMoviesAdapter
    private lateinit var pop_tvs_adapter: PopularTvAdapter
    private lateinit var up_movies_adapter: UpcommingMoviesAdapter
    private lateinit var up_tvs_adapter: UpcommingTvsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        greetings = view.findViewById(R.id.userName)
        val name = sharedPreferences.getString("userName", "User")
        greetings.text = name

        val viewModelFactory = HomeViewModelFactory(RepositoryImplementation(NetworkDSImplementaion))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)


        val pop_movies_rv : RecyclerView = view.findViewById(R.id.trending_movies)
        pop_movies_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        pop_movies_rv.addItemDecoration(SpaceItemDecoration(20))

        val up_movies_rv : RecyclerView = view.findViewById(R.id.upcommingMovies_rv)
        up_movies_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        up_movies_rv.addItemDecoration(SpaceItemDecoration(20))




        val pop_tv_rv : RecyclerView = view.findViewById(R.id.trending_tv)
        pop_tv_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        pop_tv_rv.addItemDecoration(SpaceItemDecoration(20))

        val up_tv_rv : RecyclerView = view.findViewById(R.id.upcommingTv_rv)
        up_tv_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        up_tv_rv.addItemDecoration(SpaceItemDecoration(20))


        pop_tvs_adapter = PopularTvAdapter(viewModel.pop_tvs.value?: emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(tvId = it)
            findNavController().navigate(action)
        }
        pop_tv_rv.adapter = pop_tvs_adapter

        pop_movies_adapter = TrendingMoviesAdapter(viewModel.pop_movies.value?: emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId = it)
            findNavController().navigate(action)
        }
        pop_movies_rv.adapter = pop_movies_adapter

        up_movies_adapter = UpcommingMoviesAdapter(viewModel.up_movies.value?: emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieId = it)
            findNavController().navigate(action)
        }
        up_movies_rv.adapter = up_movies_adapter

        up_tvs_adapter = UpcommingTvsAdapter(viewModel.up_tvs.value?: emptyList()) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(tvId = it)
            findNavController().navigate(action)
        }
        up_tv_rv.adapter = up_tvs_adapter


        viewModel.up_movies.observe(viewLifecycleOwner) {
            if (it != null) {
                up_movies_adapter.updateData(it)
                Log.d("asd", "Up Movies Has Changed")
            }
        }


        viewModel.pop_movies.observe(viewLifecycleOwner) {
            pop_movies_adapter.updateData(it)
        }

        viewModel.pop_tvs.observe(viewLifecycleOwner) {
            pop_tvs_adapter.updateData(it)
        }

        viewModel.up_tvs.observe(viewLifecycleOwner) {
            if (it != null) {
                up_tvs_adapter.updateData(it)
            }
        }


        viewModel.getPopularTv()
        viewModel.getUpcomingMovies()
        viewModel.getOnTheAirTvs()
        viewModel.getPopularMovies()


    }

}