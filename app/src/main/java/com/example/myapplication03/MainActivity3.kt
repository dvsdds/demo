package com.example.myapplication03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.myapplication03.data.AppDatabase
import kotlinx.coroutines.launch

class MainActivity3 : AppCompatActivity() {
    private lateinit var database: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        supportActionBar?.hide()
        // 初始化数据库
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()

        // 获取输入框和按钮的引用
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)

        // 设置按钮点击事件
        buttonDelete.setOnClickListener {
            val nameToDelete = editTextName.text.toString()

            AlertDialog.Builder(this).apply {
                setTitle("提示")
                setMessage("此操作将会删除用户！")
                setCancelable(false)
                //确认删除
                setPositiveButton("OK"){dialog,which->
                    // 在协程中删除用户
                    lifecycleScope.launch {
                        val userToDelete = database.userDao().getAllUsers().find { it.name == nameToDelete }

                        if (userToDelete != null) {
                            database.userDao().deleteUser(userToDelete)
                            println("Deleted user: $nameToDelete")
                            //删除成功通知
                            //在主线程上显示Toast
                            runOnUiThread{
                                Toast.makeText(this@MainActivity3,"删除成功！", Toast.LENGTH_SHORT).show()
                            }
                            // 创建 Intent 并跳转到 MainActivity
                            val intent = Intent(this@MainActivity3, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            runOnUiThread{
                                Toast.makeText(this@MainActivity3,"用户未找到！",Toast.LENGTH_SHORT).show()
                            }
                            println("User not found: $nameToDelete")
                        }
                    }
                }
                //取消删除
                setNegativeButton("Cancel"){dialog,which->
                    dialog.dismiss()
                }
                show()
            }
        }
    }
}