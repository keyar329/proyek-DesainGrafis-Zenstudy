package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val logoBox = findViewById<FrameLayout>(R.id.logoBox)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvTagline = findViewById<TextView>(R.id.tvTagline)
        val interpolator = DecelerateInterpolator()

        // 1. Logo fade in
        logoBox.animate()
            .alpha(1f)
            .setDuration(900)
            .setInterpolator(interpolator)
            .withEndAction {

                // 2. Judul muncul setelah logo selesai
                tvTitle.animate()
                    .alpha(1f)
                    .translationY(0f)
                    .setDuration(600)
                    .setInterpolator(interpolator)
                    .withEndAction {

                        // 3. Tagline muncul setelah judul selesai
                        tvTagline.animate()
                            .alpha(1f)
                            .translationY(0f)
                            .setDuration(600)
                            .setInterpolator(interpolator)
                            .withEndAction {

                                handler.postDelayed({
                                    val auth = com.google.firebase.auth.FirebaseAuth.getInstance()
                                    if (auth.currentUser != null) {
                                        // Sudah login sebelumnya → langsung ke MainActivity
                                        startActivity(Intent(this, MainActivity::class.java))
                                    } else {
                                        // Belum login → ke GetStarted
                                        startActivity(Intent(this, GetStartedActivity::class.java))
                                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                                    }
                                    finish()
                                }, 1200)
                            }
                            .start()
                    }
                    .start()
            }
            .start()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}