package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        supportActionBar?.hide()

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        // User Data
        val tvName = findViewById<TextView>(R.id.tvProfileName)
        val tvEmail = findViewById<TextView>(R.id.tvProfileEmail)
        val tvInitialCard = findViewById<TextView>(R.id.tvProfileInitialCard)
        val tvInitialTop = findViewById<TextView>(R.id.tvProfileInitialTop)

        val name = if (!user?.displayName.isNullOrEmpty()) {
            user?.displayName
        } else {
            user?.email?.substringBefore("@")?.replaceFirstChar { it.uppercase() }
        } ?: "Pengguna"
        val email = user?.email ?: "email@example.com"
        val initial = name.firstOrNull()?.uppercaseChar()?.toString() ?: "U"

        tvName.text = name
        tvEmail.text = email
        tvInitialCard.text = initial
        tvInitialTop.text = initial

        // Menu Keluar
        val btnKeluar = findViewById<LinearLayout>(R.id.btnKeluar)
        btnKeluar.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, GetStartedActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_profil
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_belajar -> {
                    startActivity(Intent(this, KatalogBelajarActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_timer -> {
                    startActivity(Intent(this, TimerActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profil -> true
                else -> false
            }
        }
    }
}
