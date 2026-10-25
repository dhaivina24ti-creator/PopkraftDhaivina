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

class EmpatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_empat, container, false
        )

        // Nama event
        val nama = arrayOf(
            "Mini Concert Labuai",
            "Sore Akustik Lokal",
            "Malam Musik Indie",
            "Jazz di Halaman",
            "Festival Band Lokal",
            "Pentas Musik Kreatif"
        )

        // Tanggal event
        val tanggal = arrayOf(
            "20 Oktober 2026",
            "31 Oktober 2026",
            "7 November 2026",
            "8 November 2026",
            "15 November 2026",
            "22 November 2026"
        )

        // Lokasi event
        val lokasi = arrayOf(
            "Pekanbaru",
            "Kedai Kala",
            "Ruang Temu",
            "Kopi Sela",
            "Pekanbaru",
            "Taman Kota"
        )

        // Harga tiket
        val harga = arrayOf(
            "Rp45.000",
            "Rp25.000",
            "Rp45.000",
            "Rp40.000",
            "Rp50.000",
            "Rp35.000"
        )

        // Foto sesuai nama file drawable kamu
        val gambar = arrayOf(
            R.drawable.miniconcert,
            R.drawable.akustik,
            R.drawable.indie,
            R.drawable.jazz,
            R.drawable.bandlokal,
            R.drawable.pentasseni
        )

        // ID tombol dari XML
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
