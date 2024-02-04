package com.example.adaverse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adaverse.databinding.ActivityLiveTvBinding
import com.example.adaverse.databinding.ItemLiveTvBinding
import com.example.adaverse.model.LiveTvModelResponse
import com.example.adaverse.model.LivetvRecommendation

class LiveTvAdapter(private val liveTvModelResponse: LiveTvModelResponse,private val context: Context): RecyclerView.Adapter<LiveTvAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemLiveTvBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLiveTvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return liveTvModelResponse.livetv_recommendations.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=liveTvModelResponse.livetv_recommendations[position]
        holder.binding.apply {
            title.text=currentItem.title
            Glide.with(context)
                .load(currentItem.network_thumbnail) // image url
                .override(400, 200) // resizing
                .centerCrop()
                .into(holder.binding.image);  // imageview object*/


        }
    }
}