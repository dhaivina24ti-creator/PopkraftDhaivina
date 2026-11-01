package com.example.popkraft_caca

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.gridlayout.widget.GridLayout
import com.example.popkraft_caca.databinding.ActivityCariEventBinding
import com.example.popkraft_caca.databinding.ItemCariKategoriBinding
import com.example.popkraft_caca.pertemuan5.LimaActivity

class CariEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCariEventBinding

    private data class Kategori(
        val nama: String,
        val gambar: Int
    )

    private data class Event(
        val nama: String,
        val kategori: String,
        val tanggal: String,
        val lokasi: String,
        val harga: String,
        val gambar: Int
    )

    private val kategoriList = listOf(
        Kategori("Pop-up Market", R.drawable.kreatif),
        Kategori("Musik", R.drawable.miniconcert),
        Kategori("Workshop", R.drawable.keramik),
        Kategori("Seni Rupa", R.drawable.art),
        Kategori("Kuliner", R.drawable.festival),
        Kategori("Fashion", R.drawable.pasar),
        Kategori("Kriya", R.drawable.membatik),
        Kategori("Fotografi", R.drawable.fotografi)
    )

    private val eventList = listOf(
        Event(
            "Kreatif Market Riau",
            "Pop-up Market",
            "12 - 14 Oktober 2026",
            "Taman Labuai, Pekanbaru",
            "Rp35.000",
            R.drawable.kreatif
        ),
        Event(
            "Mini Concert Labuai",
            "Musik",
            "20 Oktober 2026",
            "Pekanbaru",
            "Rp45.000",
            R.drawable.miniconcert
        ),
        Event(
            "Workshop Keramik",
            "Workshop",
            "25 Oktober 2026",
            "Studio Tanah",
            "Rp85.000",
            R.drawable.keramik
        ),
        Event(
            "Bazar Karya Lokal",
            "Pop-up Market",
            "1 November 2026",
            "Lapangan Purna MTQ",
            "Rp20.000",
            R.drawable.bazaar
        ),
        Event(
            "Sore Akustik Lokal",
            "Musik",
            "31 Oktober 2026",
            "Kedai Kala",
            "Rp25.000",
            R.drawable.akustik
        ),
        Event(
            "Lukis Tote Bag",
            "Workshop",
            "1 November 2026",
            "Ruang Rupa",
            "Rp65.000",
            R.drawable.lukistotebag
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCariEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Supaya tidak otomatis membuka keyboard
        window.setSoftInputMode(
            android.view.WindowManager.LayoutParams
                .SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )

        binding.edtCariEvent.clearFocus()

        tampilkanGridKategori()

        binding.btnKembali.setOnClickListener {
            finish()
        }

        binding.btnCari.setOnClickListener {
            tampilkanHasil(binding.edtCariEvent.text.toString())
        }

        binding.edtCariEvent.addTextChangedListener(
            object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    tampilkanHasil(s.toString())
                }

                override fun afterTextChanged(
                    s: Editable?
                ) {}
            }
        )
    }

    private fun tampilkanGridKategori() {
        val lebarLayar = resources.displayMetrics.widthPixels
        val density = resources.displayMetrics.density
        val padding = (32 * density).toInt()
        val lebarItem = (lebarLayar - padding) / 3

        binding.gridCariKategori.removeAllViews()
        kategoriList.forEachIndexed { index, kategori ->

            val itemBinding = ItemCariKategoriBinding.inflate(
                    LayoutInflater.from(this),
                    binding.gridCariKategori,
                    false )

            itemBinding.imgKategori.setImageResource(kategori.gambar)
            itemBinding.txtNamaKategori.text = kategori.nama

            val params = GridLayout.LayoutParams(
                GridLayout.spec(index / 3),
                GridLayout.spec(index % 3))

            params.width = lebarItem
            params.height = GridLayout.LayoutParams.WRAP_CONTENT

            itemBinding.root.layoutParams = params
            itemBinding.root.setOnClickListener {
                pilihKategori(kategori.nama) }
            binding.gridCariKategori.addView(
                itemBinding.root
            )
        }
    }

    private fun pilihKategori(kategori: String) {

        binding.edtCariEvent.setText(kategori)
        binding.edtCariEvent.clearFocus()

        Toast.makeText(
            this,
            "Kategori: $kategori",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun tampilkanHasil(keyword: String) {

        val pencarian = keyword.trim()

        if (pencarian.isEmpty()) {
            binding.txtJudulHasil.visibility = View.GONE
            binding.containerHasil.visibility = View.GONE
            binding.containerHasil.removeAllViews()
            return
        }

        val hasil = eventList.filter { event ->
            event.nama.contains(pencarian, true) ||
                    event.kategori.contains(pencarian, true) ||
                    event.lokasi.contains(pencarian, true)
        }

        binding.txtJudulHasil.visibility = View.VISIBLE
        binding.containerHasil.visibility = View.VISIBLE
        binding.containerHasil.removeAllViews()

        if (hasil.isEmpty()) {
            val kosong = TextView(this).apply {
                text = "Event tidak ditemukan"
                textSize = 14f
                setPadding(12, 24, 12, 24)
            }

            binding.containerHasil.addView(kosong)
            return
        }

        hasil.forEach { event ->

            val kartu = TextView(this).apply {
                text = "${event.nama}\n" +
                        "${event.tanggal}\n" +
                        "${event.lokasi}\n" +
                        "${event.harga}"

                textSize = 14f

                setTextColor(
                    getColor(R.color.dark_purple)
                )

                setBackgroundResource(
                    R.drawable.bg_kategori_card
                )

                setPadding(24, 24, 24, 24)
            }

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            params.bottomMargin =
                (10 * resources.displayMetrics.density).toInt()

            kartu.layoutParams = params

            kartu.setOnClickListener {
                val intent = Intent(
                    this,
                    LimaActivity::class.java
                ).apply {
                    putExtra("nama_event", event.nama)
                    putExtra("tanggal_event", event.tanggal)
                    putExtra("lokasi_event", event.lokasi)
                    putExtra("harga_event", event.harga)
                    putExtra("gambar_event", event.gambar)
                }

                startActivity(intent)
            }

            binding.containerHasil.addView(kartu)
        }
    }
}
