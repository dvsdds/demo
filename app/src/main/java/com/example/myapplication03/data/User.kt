package com.example.myapplication03.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 自动生成的主键
    var name: String,
    var age: Int,
    var gender: String
)