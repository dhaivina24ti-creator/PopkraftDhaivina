package com.example.popkraft_caca

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.databinding.ActivityMainBinding
import com.example.popkraft_caca.pertemuan6.SatuFragment
import com.example.popkraft_caca.pertemuan6.DuaFragment
import com.example.popkraft_caca.pertemuan6.TigaFragment
import com.example.popkraft_caca.pertemuan6.EmpatFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pilihKategori(0)

        // Menampilkan kategori Semua
        if (savedInstanceState == null) {
            replaceFragment(SatuFragment())
        }

        binding.btnSemua.setOnClickListener {
            replaceFragment(SatuFragment())
            pilihKategori(0)
        }

        binding.btnMarket.setOnClickListener {
            replaceFragment(DuaFragment())
            pilihKategori(1)
        }

        binding.btnWorkshop.setOnClickListener {
            replaceFragment(TigaFragment())
            pilihKategori(2)
        }

        binding.btnMusik.setOnClickListener {
            replaceFragment(EmpatFragment())
            pilihKategori(3)
        }
        // Tombol Logout
        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah kamu yakin ingin keluar dari PopKraf?")
                .setPositiveButton("Ya") { dialog, _ ->

                    val sharedPref = getSharedPreferences(
                        "user_pref", MODE_PRIVATE
                    )

                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

                    dialog.dismiss()

                    val intent = Intent(
                        this,
                        LoginActivity::class.java
                    )
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    private fun pilihKategori(pilihan: Int) {

        val tombol = listOf(
            binding.btnSemua,
            binding.btnMarket,
            binding.btnWorkshop,
            binding.btnMusik
        )

        for (i in tombol.indices) {
            if (i == pilihan) {
                tombol[i].backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        getColor(R.color.purple)
                    )
                tombol[i].setTextColor(getColor(R.color.white))
            } else {
                tombol[i].backgroundTintList =
                    android.content.res.ColorStateList.valueOf(
                        getColor(R.color.light_purple)
                    )
                tombol[i].setTextColor(getColor(R.color.dark_purple))
            }
        }
    }

}
