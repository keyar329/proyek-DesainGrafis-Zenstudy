# ZenStudy

**ZenStudy** adalah aplikasi Android berbasis pembelajaran dan pengatur fokus mandiri yang dirancang untuk membantu siswa dan mahasiswa belajar secara terstruktur, tenang, dan efisien. Dengan mengusung filosofi *"Fokus hari ini, tenang esok hari"*, ZenStudy menggabungkan materi latihan persiapan ujian (seperti UTBK/SNBT), penjejak energi belajar, kuis interaktif, serta pengatur waktu fokus (*Pomodoro Timer*) untuk mencegah *burnout*.

---

## 📱 Tentang Aplikasi

- **Apa itu ZenStudy?**  
  ZenStudy adalah aplikasi mobile pembelajaran hibrida yang mengombinasikan materi latihan terstruktur dengan alat pengatur manajemen waktu dan energi belajar.

- **Masalah/Kebutuhan yang Dibantu:**  
  Banyak pelajar mengalami kelelahan mental (*burnout*) dan ketidakteraturan jadwal saat mempersiapkan ujian skala besar. ZenStudy hadir untuk membantu pelajar mengenali kondisi energi harian mereka, memilih metode belajar yang tepat, serta menjaga ritme belajar yang sehat dan konsisten.

- **Tujuan Aplikasi:**  
  Menyediakan lingkungan belajar digital yang tenang, minim distraksi, personal, serta mendukung pembentukan kebiasaan belajar yang berkelanjutan tanpa memicu stres berlebih.

- **Target Pengguna:**  
  Siswa SMA/SMK/MA yang sedang mempersiapkan UTBK/SNBT, mahasiswa, serta pembelajar mandiri yang membutuhkan alat pengatur fokus dan materi latihan terstruktur.

---

## ✨ Fitur

Berikut adalah fitur-fitur yang telah terimplementasi di dalam aplikasi ZenStudy:

- **🔐 Autentikasi Pengguna (Firebase Auth)**  
  Sistem pendaftaran (*Register*) dan masuk (*Login*) akun menggunakan email dan kata sandi yang terintegrasi langsung dengan Firebase Authentication, serta fitur *Sign Out* pada profil pengguna.

- **⚡ Penjejak Energi & Mode Belajar (*Mood & Energy Tracking*)**  
  Pengguna dapat memilih level energi harian (*Semangat*, *Biasa Aja*, *Ngantuk*, atau *Burnout*) untuk mendapatkan rekomendasi mode belajar yang paling sesuai:
  - **Deep Work:** Untuk materi berat & latihan soal intensif saat energi penuh.
  - **Quick Review:** Sesi singkat untuk mengulang konsep kunci.
  - **Chill Study:** Membaca santai saat energi sedang rendah.

- **📚 Katalog & Progress Materi Belajar**  
  Katalog materi terstruktur untuk persiapan ujian yang mencakup:
  - *TPS: Penalaran Umum*
  - *Literasi Bahasa Indonesia*
  - *Literasi Bahasa Inggris*
  - *Pengetahuan Kuantitatif*  
  Dilengkapi dengan indikator persentase kemajuan (*progress bar*) tiap mata pelajaran serta bilah pencarian (*search bar*) bab/sub-bab.

- **📖 Modul Pembelajaran Interaktif**  
  Akses ke modul bacaan, konsep kunci, poin-poin penting, serta ringkasan materi sebelum memulai latihan soal.

- **📝 Kuis & Latihan Soal TPS Interaktif**  
  - Latihan soal penalaran logis dengan timer pengerjaan *real-time*.
  - Opsi jawaban interaktif dengan umpan balik warna instan (*Hijau* untuk jawaban benar, *Merah* untuk jawaban salah).
  - Navigasi soal (*Sebelumnya*, *Lanjut*, dan *Skip*).

- **📊 Laporan & Analisis Hasil Kuis**  
  Tampilan rekapitulasi setelah menyelesaikan kuis yang menyajikan skor (jumlah benar/total), persentase akurasi, dan total waktu pengerjaan, disertai tombol coba ulang atau kembali ke beranda.

- **⏱️ Sesi Fokus & Pomodoro Timer (Jetpack Compose)**  
  Fitur pengatur waktu fokus berbasis teknik Pomodoro dengan tampilan visual Neobrutalism yang menyediakan:
  - Mode timer: **Fokus (25 menit)**, **Istirahat Pendek (5 menit)**, dan **Istirahat Panjang (15 menit)**.
  - Indikator kemajuan sesi (Sesi 1 dari 4).
  - Kontrol timer (*Mulai/Jeda*, *Reset*, *Lewati Sesi*).
  - Kartu "Tips Zen" untuk panduan menjaga fokus.

- **🔥 Pelacak Konsistensi Belajar (*Streak*)**  
  Visualisasi riwayat keaktifan belajar harian pengguna selama 7 hari terakhir untuk membangun kebiasaan belajar yang konsisten.

- **👤 Profil Pengguna**  
  Menampilkan informasi nama pengguna, email, avatar inisial, serta akses cepat menuju pengaturan keluar akun.

---

## 🎨 Desain dan Konsep

- **Gaya Visual:**  
  ZenStudy menerapkan kombinasi antara **Neobrutalism Modern** (pada komponen Jetpack Compose Timer) dan **Clean Minimalist Layout**. Pendekatan Neobrutalism memberikan batas garis (*border*) tebal berwarna hitam, sudut melengkung (*rounded corners*), serta bayangan tegas (*hard shadows*).
- **Penggunaan Warna:**  
  - *Sage Green* (`#86D9A0` / `#94FBAB`): Warna utama yang melambangkan ketenangan, ketenangan pikiran (Zen), keharmonisan, dan fokus.
  - *Zen Black* (`#1A1A1A` / `#000000`): Dipakai untuk teks utama, border tebal, dan elemen kontras tinggi.
  - *Zen White* (`#FAFAF8` / `#FFFFFF`): Latar belakang bersih untuk menjaga keterbacaan.
  - Accent Colors: Kuning, Hijau, Biru, dan Oranye sebagai indikator status energi/mood harian.
- **Layout & Navigasi:**  
  Menggunakan *Bottom Navigation Bar* empat menu (*Home*, *Belajar*, *Timer*, dan *Profil*) untuk kemudahan akses dengan satu ibu jari, dipadukan dengan *Navigation Drawer* pada halaman Timer.
- **Keterkaitan Konsep Desain Grafis:**  
  Elemen UI ZenStudy dirancang dengan mempertimbangkan *psikologi warna* dan *hierarki visual*. Penggunaan warna hijau sage bertujuan menurunkan tingkat stres pengguna saat belajar, sementara gaya Neobrutalism memberikan kontras visual yang jelas dan menyenangkan bagi pengguna generasi muda.

---

## 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman:** [Kotlin](https://kotlinlang.org/) (v2.2.10)
- **IDE:** [Android Studio](https://developer.android.com/studio) (Target SDK 36, Min SDK 24)
- **UI Framework:** 
  - **Android View System (XML Layouts)** dengan `AppCompatActivity`, `ConstraintLayout`/`LinearLayout`, dan `MaterialComponents`
  - **[Jetpack Compose](https://developer.android.com/jetpack/compose)** (`Material3`, `Activity Compose`, `Custom Layouts & Modifiers`) untuk Timer Pomodoro & komponen Neobrutalism
- **Backend & Autentikasi:** [Google Firebase Authentication](https://firebase.google.com/docs/auth) (`firebase-auth:24.1.0`)
- **Build System:** Gradle dengan Kotlin DSL (`build.gradle.kts`) & Version Catalog (`libs.versions.toml`)

---

## 📂 Struktur Project

```text
Zenstudy2/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/zenstudy/     # Source Code Utama Kotlin
│   │       │   ├── ui/theme/                  # Tema, Warna, & Aktivitas Katalog
│   │       │   ├── MainActivity.kt            # Beranda & Penjejak Energy
│   │       │   ├── TimerActivity.kt           # Timer Pomodoro (Jetpack Compose)
│   │       │   ├── QuizTpsActivity.kt         # Halaman Kuis & Latihan Soal
│   │       │   ├── QuizResultActivity.kt      # Halaman Hasil & Rekap Kuis
│   │       │   ├── DetailMateriActivity.kt    # Detail Bab & Opsi Belajar
│   │       │   ├── BelajarKontenActivity.kt   # Konten Modul Bacaan
│   │       │   ├── ProfilActivity.kt          # Profil & Keluar Akun
│   │       │   ├── LoginActivity.kt           # Masuk Akun Firebase
│   │       │   ├── RegisterActivity.kt        # Pendaftaran Akun Firebase
│   │       │   ├── SplashActivity.kt          # Splash Screen & Cek Sesi
│   │       │   └── GetStartedActivity.kt      # Halaman Onboarding awal
│   │       ├── res/                           # Resources
│   │       │   ├── drawable/                  # Vector asset, background, & ikon UI
│   │       │   ├── layout/                    # Layout XML untuk Activity
│   │       │   ├── menu/                      # Menu Bottom Navigation
│   │       │   └── values/                    # Style, warna, & string
│   │       └── AndroidManifest.xml            # Deklarasi komponen & permission aplikasi
│   └── build.gradle.kts                       # Dependencies & konfigurasi build modul app
├── gradle/
│   └── libs.versions.toml                     # Version Catalog Gradle
├── build.gradle.kts                           # Top-level Gradle configuration
└── README.md                                  # Dokumentasi utama project
```

---

## 📸 Tampilan Aplikasi

Berikut adalah placeholder tangkapan layar (*screenshot*) tampilan aplikasi ZenStudy:

### 📱 Splash Screen & Onboarding
*Layar pembuka dengan animasi fade-in logo dan tombol navigasi Get Started.*
```
[ Placeholder Screenshot: Splash Screen & Onboarding ]
```

### 🏠 Beranda & Penjejak Energi
*Halaman utama yang menampilkan salam pengguna, pemilih level energi harian, rekomendasi mode belajar, dan penjejak konsistensi.*
```
[ Placeholder Screenshot: Beranda & Mood Tracker ]
```

### 📚 Katalog Materi Belajar
*Daftar mata pelajaran UTBK (TPS, Literasi, Kuantitatif) beserta persentase progress bar dan kolom pencarian.*
```
[ Placeholder Screenshot: Katalog Belajar & Progress Bar ]
```

### 📖 Detail & Modul Materi
*Detail bab pembelajaran, daftar sub-bab, serta modul bacaan materi.*
```
[ Placeholder Screenshot: Detail Materi & Modul Bacaan ]
```

### 📝 Kuis & Latihan Soal TPS
*Sesi latihan soal interaktif dengan timer pengerjaan real-time dan konfirmasi jawaban.*
```
[ Placeholder Screenshot: Sesi Kuis TPS & Timer ]
```

### 📊 Laporan & Hasil Kuis
*Rekapitulasi skor akhir, persentase akurasi, dan waktu pengerjaan kuis.*
```
[ Placeholder Screenshot: Laporan Hasil Kuis ]
```

### ⏱️ Sesi Fokus (Pomodoro Timer)
*Pengatur waktu fokus Neobrutalism dengan Pilihan Tab Fokus/Istirahat, Lingkaran Timer, dan Tips Zen.*
```
[ Placeholder Screenshot: Timer Pomodoro Neobrutalism ]
```

### 👤 Profil Pengguna
*Halaman profil pengguna yang menampilkan identitas email dan tombol keluar.*
```
[ Placeholder Screenshot: Profil Pengguna ]
```

---

## ▶️ Cara Menjalankan

1. **Clone Repository:**
   ```bash
   git clone https://github.com/keyar329/proyek-DesainGrafis-Zenstudy.git
   cd proyek-DesainGrafis-Zenstudy
   ```

2. **Buka Project di Android Studio:**
   - Buka Android Studio (versi Ladybug / 2024.2+ direkomendasikan).
   - Pilih **Open** lalu arahkan ke folder project `ZenStudy`.

3. **Konfigurasi Firebase:**
   - Pastikan file `google-services.json` yang valid dari proyek Firebase Anda telah diletakkan di dalam folder `app/` (jika menggunakan instance Firebase sendiri).

4. **Proses Gradle Sync:**
   - Tunggu hingga proses **Gradle Sync** dan indexing selesai.

5. **Jalankan Aplikasi:**
   - Hubungkan perangkat Android fisik (dengan USB Debugging aktif) atau gunakan Emulator Android (API Level 24 / Android 7.0 ke atas).
   - Klik tombol **Run** (atau tekan `Shift + F10`) di Android Studio.

---

## 👨‍💻 Pengembang

- **Nama:** Kevin Yarden Tarigan  
- **Program Studi:** S1 Ilmu Komputer  
- **Universitas:** Universitas Sumatera Utara  

---

## 📌 Catatan

- **Minimum SDK:** Android 7.0 (API Level 24, Nougat) atau versi yang lebih tinggi.
- **Koneksi Firebase:** Fitur pendaftaran dan masuk pengguna memerlukan koneksi internet untuk dapat berkomunikasi dengan layanan Firebase Authentication.
- **Arsitektur UI Hibrida:** Aplikasi ini mendemonstrasikan integrasi hibrida antara Android View System tradisional (XML) dan Jetpack Compose modern dalam satu proyek Android Kotlin.
