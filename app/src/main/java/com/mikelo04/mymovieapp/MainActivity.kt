package com.mikelo04.mymovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikelo04.mymovieapp.api.ApiConfig
import com.mikelo04.mymovieapp.api.ApiInterface
import com.mikelo04.mymovieapp.databinding.ActivityMainBinding
import com.mikelo04.mymovieapp.response.MovieResponse
import com.mikelo04.mymovieapp.response.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val movies = mutableListOf<ResultsItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovie.setHasFixedSize(true)

        getMovieData()
    }

    private fun getMovieData() {
        val apiService = ApiConfig.getInstance().create(ApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MovieData", "Failed to fetch movie data", t)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val receivedMovies = response.body()?.results?.filterNotNull()
                    if (receivedMovies != null) {
                        Log.d("MovieData", "Received ${receivedMovies.size} movies")
                        movies.addAll(receivedMovies)
                        binding.rvMovie.adapter = MovieAdapter(movies)
                    } else {
                        Log.e("MovieData", "Received movies list is null")
                    }
                } else {
                    Log.e("MovieData", "Failed to fetch movie data. Response code: ${response.code()}")
                }
            }
        })
    }

}
