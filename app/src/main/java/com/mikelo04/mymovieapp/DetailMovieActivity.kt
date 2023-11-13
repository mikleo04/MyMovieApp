package com.mikelo04.mymovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mikelo04.mymovieapp.databinding.ActivityDetailMovieBinding
import com.mikelo04.mymovieapp.response.ResultsItem

class DetailMovieActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.ivPosterDetail.clipToOutline = true

        val detailMovies = intent.getParcelableExtra<ResultsItem>(EXTRA_MOVIES)

        if (detailMovies != null){
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
            Glide.with(this).load(IMAGE_BASE + detailMovies.posterPath).into(binding.ivPosterDetail)
            binding.tvTitleDetail.text = detailMovies.title
            binding.tvItemDescription.text = detailMovies.overview

        }
    }

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }
}