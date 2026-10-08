# ShopiKu: E-Commerce Marketplace Android App

![App Version](https://img.shields.io/badge/Version-1.0-gold)
![Android Version](https://img.shields.io/badge/Android-API_24+-navy)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue)

ShopiKu adalah aplikasi E-Commerce / Marketplace Native Android (Kotlin) yang dirancang secara profesional dengan mengutamakan performa, arsitektur yang rapih, dan *User Experience* (UX) yang elegan. Aplikasi ini dirombak dengan identitas desain khusus perpaduan **Navy Blue** dan **Gold** yang eksklusif, memberikan kesan *premium*, aman, dan terpercaya bagi penggunanya.

## ✨ Fitur Utama
1. **Katalog Produk Dinamis & Pencarian (Live Search)**
   Terintegrasi penuh dengan REST API. Hasil pencarian akan disaring secara asinkron tanpa memuat ulang (reload) halaman secara utuh.
2. **Sistem Keranjang Belanja (CRUD)**
   Fungsionalitas penuh keranjang: penambahan produk, pengurangan kuantitas, penghapusan, dan kalkulasi dinamis _Subtotal_ yang sinkron dengan backend (*Live calculation*).
3. **Checkout Terintegrasi**
   Alur proses transaksi mulai dari Ringkasan Order, Alamat, Pemilihan Kurir Pengiriman, hingga opsi *Payment Gateway*.
4. **Sandbox Pembayaran Virtual Account (VA)**
   Simulasi _payment gateway_ menggunakan batas waktu pembayaran (_Countdown Timer 24 Jam_) beserta sistem validasi transaksi sukses yang interaktif.

## 🛠️ Teknologi & Arsitektur
Aplikasi ini dikembangkan dengan kaidah *Modern Android Development* (MAD):
- **Bahasa**: Kotlin.
- **Arsitektur**: MVVM (Model-View-ViewModel) memisahkan urusan UI dan bussiness/data logic secara jelas.
- **Networking**: Retrofit2 & GSON untuk komunikasi HTTP & JSON parsing dengan `MockAPI`.
- **Image Loading**: Glide (Loading gambar cepat dan *caching* efisien).
- **Navigation**: Jetpack Navigation Component (Single-Activity Architecture).
- **Concurrency**: Kotlin Coroutines & ViewModelScope.
- **UI & Layout**: ViewBinding, Material Components (Material 3), ConstraintLayout, dan CoordinatorLayout.

## 🚀 Cara Menjalankan Project
Bagi Developer atau Tester yang ingin menjalankan kode sumber ini di mesin lokal:

1. Pastikan Anda memiliki **Android Studio** versi terbaru (Minimal Koala / 2024+).
2. Lakukan *Clone* repositori ini:
   ```bash
   git clone https://github.com/MHaikalDwikiA/e-commerce.git
   ```
3. Buka folder `ECommerce` di Android Studio.
4. Tunggu hingga proses **Gradle Sync** selesai.
5. Klik icon **Run ('app')** (atau tekan `Shift + F10`) untuk meluncurkan aplikasi ke Emulator Android atau Perangkat Fisik (Minimal Android 7.0 / API 24).

## 📦 Unduh APK
Bagi pengguna awam (Non-Developer) yang hanya ingin menguji coba aplikasi secara langsung di HP Android tanpa perlu repot kompilasi, silakan unduh file installer aplikasinya:

👉 **[Unduh ShopiKu-App.apk (Debug Version)](./ShopiKu-App.apk)**

**Cara Instalasi:**
1. Download file `ShopiKu-App.apk` ke HP Android Anda.
2. Buka file tersebut. Jika muncul peringatan keamanan, silakan pilih **"Install anyway"** (karena ini versi Sandbox/Development).
3. Jika HP meminta izin untuk *Install dari Sumber Tidak Dikenal* (Install unknown apps), silakan izinkan melalui Pengaturan HP.
4. Buka aplikasi ShopiKu dan selamat berbelanja!

---
*Developed with ❤️ as E-Commerce Cloning Project.*