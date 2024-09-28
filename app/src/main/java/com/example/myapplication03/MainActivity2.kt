package com.example.myapplication03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myapplication03.data.AppDatabase
import com.example.myapplication03.data.User
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()

        // 获取 EditText 和 Button 的引用
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val editTextGender = findViewById<EditText>(R.id.editTextGender)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        // 创建 Room 数据库实例
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()

        // 设置按钮点击事件
        buttonSubmit.setOnClickListener {
            val name = editTextName.text.toString()
            val age = editTextAge.text.toString().toIntOrNull() ?: 0 // 如果输入无效，则默认为 0
            val gender = editTextGender.text.toString()

            // 插入新用户
            val newUser = User(name = name, age = age, gender = gender)

            // 使用协程插入数据
            lifecycleScope.launch {
                database.userDao().insertUser(newUser)

                // 查询并打印所有用户
                val users = database.userDao().getAllUsers()
                users.forEach {
                    println("User: ${it.name}, Age: ${it.age}, Gender: ${it.gender}")
                }
            }

            //插入成功通知
            Toast.makeText(this,"插入成功！",Toast.LENGTH_SHORT).show()

            // 创建 Intent 并跳转到 MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}