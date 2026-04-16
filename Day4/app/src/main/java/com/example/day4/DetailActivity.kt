package com.example.day4

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // [FLOW 4]: Nhận dữ liệu dựa trên nhãn "task_name"
        // Dòng này cực quan trọng: ?: "No data" là cách xử lý khi shipper (Intent) không mang gì đến
        val name = intent.getStringExtra("task_name") ?: "No data"

        // Hiển thị lên TextView (ID tvDetailName mày đã đặt ở file XML màn 2)
        val tvDetail = findViewById<TextView>(R.id.tvDetailName)
        tvDetail.text = name
    }
}