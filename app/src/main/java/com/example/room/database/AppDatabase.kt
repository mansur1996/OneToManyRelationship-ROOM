package com.example.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.dao.CategoryDao
import com.example.room.dao.NewsDao
import com.example.room.entity.Category
import com.example.room.entity.News

@Database(entities = [News::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun newsDao() : NewsDao
    abstract fun categoryDao() : CategoryDao

    companion object{
        private var instance : AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : AppDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "news.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}