package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class KatalogBelajarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_katalog_belajar)
        supportActionBar?.hide()

        // Set profile initial
        val tvProfile = findViewById<TextView>(R.id.tvProfileInitial)
        val user = FirebaseAuth.getInstance().currentUser
        val initial = user?.email?.firstOrNull()?.uppercaseChar()?.toString() ?: "K"
        tvProfile.text = initial
        tvProfile.setOnClickListener {
            startActivity(Intent(this, ProfilActivity::class.java))
        }

        findViewById<android.view.View>(R.id.cardTPS).setOnClickListener {
            startActivity(Intent(this, DetailMateriActivity::class.java))
        }

        // Bottom navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_belajar
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_belajar -> true
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
    }
}