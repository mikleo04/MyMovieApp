package com.mikelo04.mymovieapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikelo04.mymovieapp.response.MovieResponse
import com.mikelo04.mymovieapp.response.ResultsItem

class MovieAdapter(private val movies : MutableList<ResultsItem>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie: ResultsItem) {
            val title = itemView.findViewById<TextView>(R.id.tv_title)
            val poster = itemView.findViewById<ImageView>(R.id.iv_poster)
            title.text = movie.title
            Glide.with(itemView).load(IMAGE_BASE + movie.posterPath).into(poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(movies.get(position))

        holder.itemView.setOnClickListener {
            moveToMoviesDetail(movie, it)
        }
    }

    private fun moveToMoviesDetail(movie: ResultsItem, it: View) {
        val detailMoviesIntent = Intent(it.context, DetailMovieActivity::class.java)
        detailMoviesIntent.putExtra(DetailMovieActivity.EXTRA_MOVIES, movie)
        it.context.startActivity(detailMoviesIntent)
    }

}