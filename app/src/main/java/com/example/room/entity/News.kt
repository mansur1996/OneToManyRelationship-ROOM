package com.example.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Creating table
//Entity - annotation
@Entity(tableName = "news_table")
class News{
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null

    @ColumnInfo(name = "news_title")
    var title : String? = null

    @ColumnInfo(name = "description")
    var desc : String? = null

    @ColumnInfo(name = "category_id")
    var categoryId : Int? = null

    constructor()
}
