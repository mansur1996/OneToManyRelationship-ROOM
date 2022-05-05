package com.example.room.dao

import androidx.room.*
import com.example.room.entity.CategoryNews
import com.example.room.entity.News

//Dao - annotation
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(news: News)

    @Delete
    fun deleteNews(news: News)

    @Update
    fun updateNews(news: News)

    @Query("select * from NEWS_TABLE")
    fun getAllNews() : List<News>

    @Query("select * from news_table where id = :id")
    fun getNewsByID(id : Int) : News

    @Query("select id from news_table where news_title=:title and description=:desc")
    fun getNewsByID(title: String, desc : String) : Int

}