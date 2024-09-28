package com.example.myapplication03

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

class TitleLayout(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.activity_title_layout, this)

        val titleBack=findViewById<Button>(R.id.titleBack)
        titleBack.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
    }
}