package com.example.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemNewsBinding
import com.example.room.entity.News

class NewsAdapter (val list : List<News>, val onItemClickListener: OnItemClickListener ) : RecyclerView.Adapter<NewsAdapter.VH>(){

    inner class VH(var itemNewsBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemNewsBinding.root){
        fun onBind(news: News, position: Int){
            itemNewsBinding.tvTitle.text = news.title
            itemNewsBinding.tvDesc.text = news.desc

            itemNewsBinding.btnDelete.setOnClickListener {
                onItemClickListener.onItemDelete(news, position)
            }

            itemNewsBinding.btnEdit.setOnClickListener {
                onItemClickListener.onItemEdit(news, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemNewsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickListener{
        fun onItemDelete(news: News, position: Int)
        fun onItemEdit(news: News, position: Int)
    }
}