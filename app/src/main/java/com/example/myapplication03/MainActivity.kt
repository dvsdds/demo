package com.example.myapplication03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初始化按钮并设置点击事件
        val buttonCreate: Button = findViewById(R.id.button_create)
        buttonCreate.setOnClickListener {
            // 创建 Intent 并跳转到 MainActivity2
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        val buttonDelete: Button=findViewById(R.id.button_delete)
        buttonDelete.setOnClickListener {
            val intent=Intent(this,MainActivity3::class.java)
            startActivity(intent)
        }
        val buttonUpdate: Button=findViewById(R.id.button_update)
        buttonUpdate.setOnClickListener {
            val intent=Intent(this,MainActivity4::class.java)
            startActivity(intent)
        }
        val buttonRead: Button=findViewById(R.id.button_read)
        buttonRead.setOnClickListener {
            val intent=Intent(this,MainActivity5::class.java)
            startActivity(intent)
        }
    }
}