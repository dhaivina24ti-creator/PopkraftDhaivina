package com.example.popkraft_caca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            replaceFragment(BerandaFragment())
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_beranda -> {
                    replaceFragment(BerandaFragment())
                    true
                }
                R.id.nav_sekitarku -> {
                    replaceFragment(SekitarkuFragment())
                    true
                }
                R.id.nav_tiketku -> {
                    replaceFragment(TiketkuFragment())
                    true
                }
                R.id.nav_profil -> {
                    replaceFragment(ProfilFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
