package com.example.myapplication03

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myapplication03.data.AppDatabase
import kotlinx.coroutines.launch

class MainActivity4 : AppCompatActivity() {
    private lateinit var database: AppDatabase
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        supportActionBar?.hide()
        // 初始化数据库
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()
        // 获取 UI 组件
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)
        val editTextUserName = findViewById<EditText>(R.id.editTextUserName)
        val editTextUserAge = findViewById<EditText>(R.id.editTextUserAge)
        val editTextUserGender = findViewById<EditText>(R.id.editTextUserGender)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        //查询
        buttonSearch.setOnClickListener {
            val nameToSearch=editTextName.text.toString()
            // 在协程中查找用户
            lifecycleScope.launch {
                val user = database.userDao().getAllUsers().find { it.name == nameToSearch }
                if (user != null) {
                    // 显示用户信息并设置可编辑
                    editTextUserName.visibility = android.view.View.VISIBLE
                    editTextUserAge.visibility = android.view.View.VISIBLE
                    editTextUserGender.visibility = android.view.View.VISIBLE
                    buttonSubmit.visibility = android.view.View.VISIBLE

                    editTextUserName.setText(user.name)
                    editTextUserAge.setText(user.age.toString())
                    editTextUserGender.setText(user.gender)
                } else {
                    // 用户未找到
                    println("User not found")
                    //提示用户未找到
                    Toast.makeText(this@MainActivity4,"该用户未找到！",Toast.LENGTH_SHORT).show()
                }
            }
        }
        // 提交更新操作
        buttonSubmit.setOnClickListener {
            val updatedName = editTextUserName.text.toString()
            val updatedAge = editTextUserAge.text.toString().toInt()
            val updatedGender = editTextUserGender.text.toString()

            lifecycleScope.launch {
                var user = database.userDao().getAllUsers().find { it.name == editTextName.text.toString() }
                if (user != null) {
                    // 更新用户数据
                    user.name = updatedName
                    user.age = updatedAge
                    user.gender = updatedGender
                    database.userDao().updateUser(user)

                    println("User updated successfully")
                }
            }
            //更新成功通知
            Toast.makeText(this,"更新成功！", Toast.LENGTH_SHORT).show()

            // 创建 Intent 并跳转到 MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}