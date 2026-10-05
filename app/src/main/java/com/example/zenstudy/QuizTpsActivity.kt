package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)

class QuizTpsActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentQuestionIndex = 0
    private var selectedOptionIndex = -1
    private var isAnswered = false
    private val userAnswers = mutableMapOf<Int, Int>() // Question ID to Option Index

    private lateinit var tvTimer: TextView
    private lateinit var tvSoalCount: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvSoalLabel: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var tvOptionA: TextView
    private lateinit var tvOptionB: TextView
    private lateinit var tvOptionC: TextView
    private lateinit var tvOptionD: TextView
    private lateinit var optionContainers: List<LinearLayout>
    private lateinit var btnKonfirmasi: TextView
    private lateinit var btnSkip: TextView

    private var secondsElapsed = 0
    private val handler = Handler(Looper.getMainLooper())
    private val timerRunnable = object : Runnable {
        override fun run() {
            secondsElapsed++
            val minutes = secondsElapsed / 60
            val seconds = secondsElapsed % 60
            tvTimer.text = String.format("%02d:%02d", minutes, seconds)
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_tps)
        supportActionBar?.hide()

        initQuestions()
        initViews()
        loadQuestion()
        handler.post(timerRunnable)
    }

    private fun initQuestions() {
        questions = listOf(
            Question(1, "Jika semua dokter adalah sarjana, and Andi adalah seorang dokter, maka pernyataan yang PASTI BENAR adalah...", listOf("Andi pasti seorang sarjana", "Andi mungkin bukan sarjana", "Semua sarjana adalah dokter", "Andi bukan seorang dokter"), 0),
            Question(2, "Semua mamalia bernapas dengan paru-paru. Paus adalah mamalia. Jadi...", listOf("Paus bernapas dengan insang", "Paus bukan ikan", "Paus bernapas dengan paru-paru", "Semua yang bernapas dengan paru-paru adalah paus"), 2),
            Question(3, "Jika hari hujan, maka jalanan basah. Hari ini jalanan tidak basah. Kesimpulannya...", listOf("Hari ini hujan", "Hari ini tidak hujan", "Mungkin hari ini hujan", "Jalanan sedang diperbaiki"), 1),
            Question(4, "Beberapa siswa rajin. Semua yang rajin pasti lulus. Jadi...", listOf("Semua siswa lulus", "Beberapa siswa lulus", "Tidak ada siswa yang lulus", "Siswa tidak rajin tetap lulus"), 1),
            Question(5, "A > B dan B > C. Maka...", listOf("C > A", "A < C", "A > C", "B < C"), 2),
            Question(6, "Semua bunga berwarna wangi. Melati adalah bunga. Jadi...", listOf("Melati berwarna wangi", "Melati tidak wangi", "Hanya melati yang wangi", "Bunga mawar tidak wangi"), 0),
            Question(7, "Jika lapar, Budi makan. Budi sedang tidak makan. Jadi...", listOf("Budi sudah kenyang", "Budi tidak lapar", "Budi sedang tidur", "Budi malas makan"), 1),
            Question(8, "Setiap kendaraan butuh bahan bakar. Motor adalah kendaraan. Jadi...", listOf("Motor butuh bensin", "Motor butuh bahan bakar", "Hanya motor yang butuh bahan bakar", "Bahan bakar hanya untuk motor"), 1),
            Question(9, "Jika X = 2, maka Y = 4. Diketahui Y tidak sama dengan 4. Jadi...", listOf("X = 2", "X tidak sama dengan 2", "X = 4", "Y = 2"), 1),
            Question(10, "Beberapa burung bisa terbang. Burung unta adalah burung. Burung unta tidak bisa terbang. Jadi...", listOf("Semua burung tidak bisa terbang", "Beberapa burung tidak bisa terbang", "Burung unta bukan burung", "Terbang adalah syarat jadi burung"), 1)
        )
    }

    private fun initViews() {
        tvTimer = findViewById(R.id.tvTimer)
        tvSoalCount = findViewById(R.id.tvSoalCount)
        progressBar = findViewById(R.id.quizProgressBar)
        tvSoalLabel = findViewById(R.id.tvSoalLabel)
        tvQuestion = findViewById(R.id.tvQuestion)
        tvOptionA = findViewById(R.id.tvOptionA)
        tvOptionB = findViewById(R.id.tvOptionB)
        tvOptionC = findViewById(R.id.tvOptionC)
        tvOptionD = findViewById(R.id.tvOptionD)
        
        optionContainers = listOf(
            findViewById(R.id.optionA),
            findViewById(R.id.optionB),
            findViewById(R.id.optionC),
            findViewById(R.id.optionD)
        )

        btnKonfirmasi = findViewById(R.id.btnKonfirmasi)
        btnSkip = findViewById(R.id.btnSkip)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                loadQuestion()
            } else {
                finish()
            }
        }

        optionContainers.forEachIndexed { index, layout ->
            layout.setOnClickListener {
                if (!isAnswered) {
                    selectOption(index)
                }
            }
        }

        btnKonfirmasi.setOnClickListener {
            if (!isAnswered) {
                if (selectedOptionIndex != -1) {
                    confirmAnswer()
                }
            } else {
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    loadQuestion()
                } else {
                    finishQuiz()
                }
            }
        }

        btnSkip.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                userAnswers.remove(questions[currentQuestionIndex].id)
                currentQuestionIndex++
                loadQuestion()
            }
        }
    }

    private fun selectOption(index: Int) {
        selectedOptionIndex = index
        optionContainers.forEachIndexed { i, layout ->
            layout.setBackgroundResource(if (i == index) R.drawable.bg_option_selected else R.drawable.bg_option_default)
        }
    }

    private fun confirmAnswer() {
        isAnswered = true
        val correctIndex = questions[currentQuestionIndex].correctIndex
        userAnswers[questions[currentQuestionIndex].id] = selectedOptionIndex

        if (selectedOptionIndex == correctIndex) {
            optionContainers[selectedOptionIndex].setBackgroundResource(R.drawable.bg_option_correct)
        } else {
            optionContainers[selectedOptionIndex].setBackgroundResource(R.drawable.bg_option_wrong)
            optionContainers[correctIndex].setBackgroundResource(R.drawable.bg_option_correct)
        }

        if (currentQuestionIndex == questions.size - 1) {
            btnKonfirmasi.text = "SELESAI"
            btnSkip.visibility = View.GONE
        } else {
            btnKonfirmasi.text = "LANJUT KE SOAL BERIKUTNYA"
        }
    }

    private fun loadQuestion() {
        isAnswered = false
        selectedOptionIndex = -1
        btnKonfirmasi.text = "KONFIRMASI JAWABAN"
        btnSkip.visibility = if (currentQuestionIndex == questions.size - 1) View.GONE else View.VISIBLE

        val question = questions[currentQuestionIndex]
        tvSoalCount.text = "Soal ${currentQuestionIndex + 1} / ${questions.size}"
        progressBar.progress = currentQuestionIndex + 1
        tvSoalLabel.text = "SOAL ${currentQuestionIndex + 1}"
        tvQuestion.text = question.text
        tvOptionA.text = question.options[0]
        tvOptionB.text = question.options[1]
        tvOptionC.text = question.options[2]
        tvOptionD.text = question.options[3]

        // Reset backgrounds
        optionContainers.forEach { it.setBackgroundResource(R.drawable.bg_option_default) }
        
        // Re-apply if already answered (back navigation)
        if (userAnswers.containsKey(question.id)) {
            val ans = userAnswers[question.id]!!
            isAnswered = true
            selectedOptionIndex = ans
            val correctIndex = question.correctIndex
            if (ans == correctIndex) {
                optionContainers[ans].setBackgroundResource(R.drawable.bg_option_correct)
            } else {
                optionContainers[ans].setBackgroundResource(R.drawable.bg_option_wrong)
                optionContainers[correctIndex].setBackgroundResource(R.drawable.bg_option_correct)
            }
            if (currentQuestionIndex == questions.size - 1) {
                btnKonfirmasi.text = "SELESAI"
                btnSkip.visibility = View.GONE
            } else {
                btnKonfirmasi.text = "LANJUT KE SOAL BERIKUTNYA"
            }
        }
    }

    private fun finishQuiz() {
        handler.removeCallbacks(timerRunnable)
        var correctCount = 0
        questions.forEach { q ->
            if (userAnswers[q.id] == q.correctIndex) {
                correctCount++
            }
        }

        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra("CORRECT_COUNT", correctCount)
        intent.putExtra("TOTAL_COUNT", questions.size)
        intent.putExtra("TIME_TAKEN", tvTimer.text.toString())
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(timerRunnable)
    }
}
