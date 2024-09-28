package com.example.myapplication03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication03.data.AppDatabase
import kotlinx.coroutines.launch

class MainActivity5 : AppCompatActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        supportActionBar?.hide()
        // 获取用户信息的容器
        val userContainer = findViewById<LinearLayout>(R.id.userContainer)
        // 初始化数据库
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()
        // 从数据库中获取所有用户并显示
        lifecycleScope.launch {
            val users = database.userDao().getAllUsers()

            // 动态添加用户信息到LinearLayout中
            for (user in users) {
                val userInfo = TextView(this@MainActivity5)
                userInfo.text = "姓名: ${user.name}, 年龄: ${user.age}, 性别: ${user.gender}"
                userInfo.textSize = 18f
                userContainer.addView(userInfo)
            }
        }
    }
}