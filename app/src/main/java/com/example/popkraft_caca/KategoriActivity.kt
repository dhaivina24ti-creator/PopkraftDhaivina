package com.example.popkraft_caca

import android.content.Intent
import android.os.Bundle
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.popkraft_caca.databinding.ActivityKategoriBinding

class KategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategoriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kategoriList = ArrayList<HashMap<String, Any>>()

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Pop-up Market",
                "desc" to "Bazar dan pasar kreatif lokal"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Musik",
                "desc" to "Konser dan pertunjukan musik indie"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Workshop",
                "desc" to "Kelas dan kegiatan keterampilan"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Seni Rupa",
                "desc" to "Pameran lukisan dan ilustrasi"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Kuliner",
                "desc" to "Festival makanan lokal"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Fashion & Thrift",
                "desc" to "Bazar pakaian dan fesyen kreatif"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Kriya",
                "desc" to "Kerajinan tangan dan produk lokal"
            )
        )

        kategoriList.add(
            hashMapOf(
                "icon" to R.drawable.ic_category,
                "title" to "Fotografi",
                "desc" to "Pameran karya fotografi"
            )
        )

        val adapter = SimpleAdapter(
            this,
            kategoriList,
            R.layout.item_kategori,
            arrayOf("icon", "title", "desc"),
            intArrayOf(
                R.id.imgKategori,
                R.id.txtTitle,
                R.id.txtDesc
            )
        )

        binding.listKategori.adapter = adapter


        binding.listKategori.setOnItemClickListener { _, _, position, _ ->

            val kategori = kategoriList[position]["title"].toString()

            when (kategori) {
                "Pop-up Market", "Workshop", "Musik" -> {
                    val hasil = Intent().apply {
                        putExtra("kategori_dipilih", kategori)
                    }
                    setResult(RESULT_OK, hasil)
                    finish()
                }

                else -> {
                    Toast.makeText(
                        this,
                        "Event kategori $kategori belum tersedia",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.txtKembali.setOnClickListener {
            finish()
        }
    }
}