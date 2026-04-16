package com.example.day3

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // TAG giúp lọc trong Logcat
    private val TAG = "LIFECYCLE_LOG"

    // Hàm phụ để hiện thông báo Toast cho nhanh và gọn code
    private fun showLifecycle(message: String) {
        // Ghi vào Logcat
        Log.d(TAG, message)
        // Hiện lên màn hình điện thoại
        Toast.makeText(this, message.split(":")[0], Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLifecycle("onCreate: App bắt đầu sinh ra (Init UI)")
    }

    override fun onStart() {
        super.onStart()
        showLifecycle("onStart: App xuất hiện trước mắt")
    }

    override fun onResume() {
        super.onResume()
        showLifecycle("onResume: App sẵn sàng tương tác (Foreground)")
    }

    override fun onPause() {
        super.onPause()
        showLifecycle("onPause: Mất focus (Bị che bởi dialog hoặc app khác)")
    }

    override fun onStop() {
        super.onStop()
        showLifecycle("onStop: Biến mất hoàn toàn (Đã nhấn Home hoặc sang app khác)")
    }

    override fun onRestart() {
        super.onRestart()
        showLifecycle("onRestart: Chuẩn bị quay lại từ trạng thái Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        showLifecycle("onDestroy: Chết hẳn (Bấm Back hoặc bị hệ thống kill)")
    }
}