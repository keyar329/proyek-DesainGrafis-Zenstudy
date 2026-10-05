package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        // Ambil nama user dari Firebase
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val user = FirebaseAuth.getInstance().currentUser
        val name = if (!user?.displayName.isNullOrEmpty()) {
            user?.displayName
        } else {
            user?.email?.substringBefore("@")?.replaceFirstChar { it.uppercase() }
        } ?: "Pengguna"
        tvGreeting.text = "Halo, $name!"

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_belajar -> {
                    startActivity(Intent(this, KatalogBelajarActivity::class.java))
                    true
                }
                R.id.nav_timer -> {
                    startActivity(Intent(this, TimerActivity::class.java))
                    true
                }
                R.id.nav_profil -> {
                    startActivity(Intent(this, ProfilActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Tombol Mulai Fokus
        val btnMulaiFokus = findViewById<TextView>(R.id.btnMulaiFokus)
        btnMulaiFokus.setOnClickListener {
            Toast.makeText(this, "Sesi fokus dimulai!", Toast.LENGTH_SHORT).show()
        }
    }
}