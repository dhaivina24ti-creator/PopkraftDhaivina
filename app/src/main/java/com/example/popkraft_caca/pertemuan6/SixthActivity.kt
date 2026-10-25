package com.example.popkraft_caca.pertemuan6

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.R
import com.example.popkraft_caca.databinding.ActivitySixthBinding

class SixthActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySixthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySixthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mengatur Toolbar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            title = "Activity Sixth"
            subtitle = "Penerapan Fragment"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // Menampilkan Fragment pertama
        if (savedInstanceState == null) {
            replaceFragment(SatuFragment(), false)
        }

        // Tombol untuk mengganti Fragment
        binding.btnFragment1.setOnClickListener {
            replaceFragment(SatuFragment())
        }

        binding.btnFragment2.setOnClickListener {
            replaceFragment(DuaFragment())
        }

        binding.btnFragment3.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }

    private fun replaceFragment(
        fragment: Fragment,
        addToBackStack: Boolean = true
    ) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
