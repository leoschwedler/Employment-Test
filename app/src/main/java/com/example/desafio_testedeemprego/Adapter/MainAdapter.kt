package com.example.desafio_testedeemprego.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio_testedeemprego.databinding.ItemRvBinding
import com.squareup.picasso.Picasso

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>(){

    private var lisImage = emptyList<String>()

    fun addList(list: List<String>){
        lisImage = list
        notifyDataSetChanged()
    }

    inner class MainViewHolder(private val binding: ItemRvBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(url: String){
            Picasso.get().load(url).into(binding.imageItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val url = lisImage[position]
        holder.bind(url)

    }

    override fun getItemCount(): Int = lisImage.size
}