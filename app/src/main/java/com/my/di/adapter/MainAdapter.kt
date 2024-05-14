package com.my.di.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.di.R
import com.my.di.databinding.TopHeadlineItemLayoutBinding
import com.my.di.model.NewsData


class MainAdapter(
    private val news: ArrayList<NewsData>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewsData) {
            binding.textViewTitle.text = data.title
            Glide.with(binding.imageViewBanner.context)
                .load(data.urlToImage)
                .into(binding.imageViewBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(news[position])

    fun addData(list: List<NewsData>) {
        news.addAll(list)
    }
}