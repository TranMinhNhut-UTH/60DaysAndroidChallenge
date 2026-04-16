package com.example.day4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- LOGIC CHO NÚT 1 ---
        val btn1 = findViewById<Button>(R.id.btnTask1)
        btn1.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("task_name", "Task 1: Học Android 60 ngày")
            startActivity(intent)
        }

        // --- LOGIC CHO NÚT 2 ---
        val btn2 = findViewById<Button>(R.id.btnTask2)
        btn2.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("task_name", "Task 2: Code Intent thực tế")
            startActivity(intent)
        }

        // --- LOGIC CHO NÚT 3 ---
        val btn3 = findViewById<Button>(R.id.btnTask3)
        btn3.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("task_name", "Task 3: Kiểm tra nút Back")
            startActivity(intent)
        }
    }
}