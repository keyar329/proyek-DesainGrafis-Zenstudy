package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)
        supportActionBar?.hide()

        val correct = intent.getIntExtra("CORRECT_COUNT", 0)
        val total = intent.getIntExtra("TOTAL_COUNT", 10)
        val timeTaken = intent.getStringExtra("TIME_TAKEN") ?: "00:00"

        findViewById<TextView>(R.id.tvScore).text = "$correct / $total"
        findViewById<TextView>(R.id.tvTimeTaken).text = timeTaken
        
        val accuracy = (correct.toFloat() / total.toFloat() * 100).toInt()
        findViewById<TextView>(R.id.tvAccuracy).text = "$accuracy%"

        findViewById<TextView>(R.id.btnBackHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        findViewById<TextView>(R.id.btnTryAgain).setOnClickListener {
            val intent = Intent(this, QuizTpsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
