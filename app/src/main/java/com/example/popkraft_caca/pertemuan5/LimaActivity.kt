package com.example.popkraft_caca.pertemuan5

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.popkraft_caca.R
import com.example.popkraft_caca.databinding.ActivityLimaBinding
import com.google.android.material.snackbar.Snackbar

class LimaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLimaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLimaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menerapkan Toolbar sebagai ActionBar
        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_black)
        }

        // Mengambil data dari Fragment
        val nama = intent.getStringExtra("nama_event")
            ?: "Kreatif Market Riau"

        val tanggal = intent.getStringExtra("tanggal_event")
            ?: "12 - 14 Oktober 2026"

        val lokasi = intent.getStringExtra("lokasi_event")
            ?: "Taman Labuai, Pekanbaru"

        val harga = intent.getStringExtra("harga_event")
            ?: "Rp35.000"

        val gambar = intent.getIntExtra(
            "gambar_event",
            R.drawable.kreatif
        )

        // Menampilkan data event
        binding.txtNamaEvent.text = nama
        binding.txtTanggal.text = tanggal
        binding.txtLokasi.text = lokasi
        binding.txtHarga.text = harga
        binding.imgEvent.setImageResource(gambar)

        // Daftar event Workshop
        val daftarWorkshop = listOf(
            "Workshop Keramik",
            "Lukis Tote Bag",
            "Merangkai Manik",
            "Workshop Melukis",
            "Kelas Membatik",
            "Kelas Fotografi"
        )

        // Daftar event Musik
        val daftarMusik = listOf(
            "Mini Concert Labuai",
            "Sore Akustik Lokal",
            "Malam Musik Indie",
            "Jazz di Halaman",
            "Festival Band Lokal",
            "Pentas Musik Kreatif"
        )

        // Menentukan kategori dan deskripsi
        when {
            nama in daftarWorkshop -> {
                binding.txtKategori.text = "Workshop"

                binding.txtDeskripsi.text =
                    "Ikuti kegiatan kreatif bersama dan pelajari " +
                            "keterampilan baru melalui workshop ini. " +
                            "Temukan pengalaman menarik dan hasilkan " +
                            "karya dengan kreativitasmu sendiri."
            }

            nama in daftarMusik -> {
                binding.txtKategori.text = "Musik"

                binding.txtDeskripsi.text =
                    "Nikmati pertunjukan musik lokal bersama " +
                            "komunitas kreatif dalam suasana yang seru " +
                            "dan menyenangkan. Dukung musisi lokal " +
                            "dan rasakan pengalaman musik yang berbeda."
            }

            else -> {
                binding.txtKategori.text = "Pop-up Market"

                binding.txtDeskripsi.text =
                    "Temukan berbagai produk kreatif lokal, " +
                            "mulai dari karya seni, fashion, kuliner, " +
                            "hingga kerajinan tangan. Event ini menjadi " +
                            "tempat berkumpulnya komunitas kreatif " +
                            "untuk berbagi karya dan inspirasi."
            }
        }

        // Tombol Pesan Tiket
        binding.btnBeliTiket.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Fitur pemesanan tiket belum tersedia",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    // Fungsi tombol kembali pada Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
