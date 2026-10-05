package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GetStartedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_started)
        supportActionBar?.hide()

        // Mulai Sekarang → ke Register
        val btnMulai = findViewById<TextView>(R.id.btnMulai)
        btnMulai.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Sudah punya akun → ke Login
        val btnLogin = findViewById<TextView>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}