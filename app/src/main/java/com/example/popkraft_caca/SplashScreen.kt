package com.example.popkraft_caca

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.popkraft_caca.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val totalSlide = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        val sudahLogin = pref.getBoolean(
            "isLogin",
            false
        )

        val tutorialSelesai = pref.getBoolean(
            "tutorial_seen",
            false
        )

        // Sudah login langsung ke Beranda
        if (sudahLogin) {
            startActivity(
                Intent(this, BaseActivity::class.java)
            )
            finish()
            return
        }

        // Tutorial sudah dilihat langsung ke Login
        if (tutorialSelesai) {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
            finish()
            return
        }

        // Pengguna baru melihat tutorial
        binding = ActivitySplashScreenBinding.inflate(
            layoutInflater
        )

        setContentView(binding.root)

        val adapter = TutorialFragmentAdapter(this)

        binding.tutorialViewPager.adapter = adapter

        // Hubungkan DotsIndicator dengan ViewPager2
        binding.dotIndicator.attachTo(
            binding.tutorialViewPager
        )

        binding.tutorialViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    if (position == totalSlide - 1) {
                        binding.btnMulai.text = "Mulai Sekarang"
                        binding.btnLewati.visibility = View.INVISIBLE
                    } else {
                        binding.btnMulai.text = "Selanjutnya"
                        binding.btnLewati.visibility = View.VISIBLE
                    }
                }
            }
        )

        // Tombol Selanjutnya atau Mulai Sekarang
        binding.btnMulai.setOnClickListener {

            val halamanSekarang =
                binding.tutorialViewPager.currentItem

            if (halamanSekarang < totalSlide - 1) {

                binding.tutorialViewPager.currentItem =
                    halamanSekarang + 1

            } else {
                selesaiTutorial()
            }
        }

        // Tombol Lewati
        binding.btnLewati.setOnClickListener {
            selesaiTutorial()
        }
    }

    private fun selesaiTutorial() {

        val pref = getSharedPreferences(
            "user_pref",
            MODE_PRIVATE
        )

        pref.edit()
            .putBoolean("tutorial_seen", true)
            .apply()

        startActivity(
            Intent(this, LoginActivity::class.java)
        )

        finish()
    }
}
