package com.example.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
class Category {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
    var name : String? = null

    constructor(name: String?) {
        this.name = name
    }
}