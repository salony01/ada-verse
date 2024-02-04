package com.example.adaverse.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adaverse.ClickInterface
import com.example.adaverse.databinding.ItemGenreBinding
import com.example.adaverse.model.OttRecommendations
import com.example.adaverse.model.countModel


class GenreAdapter(private val ottRecommendations: OttRecommendations, private val context: Context,
                   var clickInterface: ClickInterface?,private val userName: String
): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemGenreBinding):
     RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return ottRecommendations.contents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=ottRecommendations.contents[position]
        holder.binding.apply {
            title.text=currentItem.title
            rating.text= currentItem.rating.toString()
            year.text=currentItem.releaseYear.toString()

            Glide.with(context)
                .load(currentItem.thumbnail) // image url
                .override(400, 200) // resizing
                .centerCrop()
                .into(holder.binding.image);  // imageview object*/


        }
        holder.itemView.setOnClickListener {

            val obj= countModel(userName,currentItem.id,"nabd")
            clickInterface?.onCellClickListener(obj)
            val website:String = currentItem.contentUrl
            //Log.i("Location , ", website);
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
            context.startActivity(browserIntent)
        }
    }


}