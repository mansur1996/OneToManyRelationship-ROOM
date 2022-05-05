package com.example.room.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.room.databinding.ItemCategoryBinding
import com.example.room.entity.Category

class CategoryAdapter(var list: List<Category>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(position : Int): Category = list[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var categoryViewHolder : CategoryViewHolder
        if(convertView == null){
            categoryViewHolder = CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent?.context)))
        }else{
            categoryViewHolder = CategoryViewHolder(ItemCategoryBinding.bind(convertView))
        }

        categoryViewHolder.itemCategoryBinding.tvCategory.text = list[position].name
        return categoryViewHolder.itemView
    }

    inner class CategoryViewHolder{
        var itemView : View
        var itemCategoryBinding : ItemCategoryBinding

        constructor(itemCategoryBinding: ItemCategoryBinding){
            itemView = itemCategoryBinding.root
            this.itemCategoryBinding = itemCategoryBinding
        }
    }
}