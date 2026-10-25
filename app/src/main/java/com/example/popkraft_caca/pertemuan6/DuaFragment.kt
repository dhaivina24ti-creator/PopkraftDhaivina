package com.example.popkraft_caca.pertemuan6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.R
import com.example.popkraft_caca.pertemuan5.LimaActivity

class DuaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_dua, container, false
        )

        val nama = arrayOf(
            "Kreatif Market Riau",
            "Bazar Karya Lokal",
            "Pasar Akhir Pekan",
            "Festival UMKM Kreatif",
            "Art Market Pekanbaru",
            "Pasar Seni Lokal"
        )

        val tanggal = arrayOf(
            "12 - 14 Oktober 2026",
            "1 November 2026",
            "7 November 2026",
            "14 November 2026",
            "21 November 2026",
            "28 November 2026"
        )

        val lokasi = arrayOf(
            "Taman Labuai, Pekanbaru",
            "Lapangan Purna MTQ",
            "Rumah Komunitas",
            "Pekanbaru",
            "Galeri Seni",
            "Taman Kreatif"
        )

        val harga = arrayOf(
            "Rp35.000",
            "Rp20.000",
            "Rp15.000",
            "Rp25.000",
            "Rp30.000",
            "Rp15.000"
        )

        val gambar = arrayOf(
            R.drawable.kreatif,
            R.drawable.bazaar,
            R.drawable.pasar,
            R.drawable.festival,
            R.drawable.art,
            R.drawable.pasarseni
        )

        val tombol = arrayOf(
            R.id.btnEvent1,
            R.id.btnEvent2,
            R.id.btnEvent3,
            R.id.btnEvent4,
            R.id.btnEvent5,
            R.id.btnEvent6
        )

        for (i in tombol.indices) {

            view.findViewById<Button>(tombol[i])
                .setOnClickListener {

                    val intent = Intent(
                        requireContext(),
                        LimaActivity::class.java
                    )

                    intent.putExtra("nama_event", nama[i])
                    intent.putExtra("tanggal_event", tanggal[i])
                    intent.putExtra("lokasi_event", lokasi[i])
                    intent.putExtra("harga_event", harga[i])
                    intent.putExtra("gambar_event", gambar[i])

                    startActivity(intent)
                }
        }

        return view
    }
}
