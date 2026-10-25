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

class TigaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_tiga, container, false
        )

        val nama = arrayOf(
            "Workshop Keramik",
            "Lukis Tote Bag",
            "Merangkai Manik",
            "Workshop Melukis",
            "Kelas Membatik",
            "Kelas Fotografi"
        )

        val tanggal = arrayOf(
            "25 Oktober 2026",
            "1 November 2026",
            "7 November 2026",
            "14 November 2026",
            "21 November 2026",
            "28 November 2026"
        )

        val lokasi = arrayOf(
            "Studio Tanah",
            "Ruang Rupa",
            "Studio Rona",
            "Galeri Seni",
            "Rumah Kreatif",
            "Creative Space"
        )

        val harga = arrayOf(
            "Rp85.000",
            "Rp65.000",
            "Rp55.000",
            "Rp75.000",
            "Rp60.000",
            "Rp90.000"
        )

        // Gambar sesuai event Workshop
        val gambar = arrayOf(
            R.drawable.keramik,
            R.drawable.lukistotebag,
            R.drawable.manik,
            R.drawable.lukis,
            R.drawable.membatik,
            R.drawable.fotografi
        )

        val tombol = arrayOf(
            R.id.btnEvent1,
            R.id.btnEvent2,
            R.id.btnEvent3,
            R.id.btnEvent4,
            R.id.btnEvent5,
            R.id.btnEvent6
        )

        // Menghubungkan tombol Lihat Detail
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
