package com.example.room.dao

import androidx.room.*
import com.example.room.entity.Category
import com.example.room.entity.CategoryNews

@Dao
interface CategoryDao {

    @Transaction
    @Query("select * from category_table")
    fun getCategoryByNews() : List<CategoryNews>

    @Query("select * from category_table")
    fun getAllCategories() : List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategory(vararg category: Category)

}