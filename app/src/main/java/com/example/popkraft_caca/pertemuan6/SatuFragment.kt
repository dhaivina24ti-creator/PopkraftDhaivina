package com.example.popkraft_caca.pertemuan6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.R
import com.example.popkraft_caca.databinding.FragmentSatuBinding

class SatuFragment : Fragment() {

    private var _binding: FragmentSatuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSatuBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Data event PopKraf yang sudah ada
        val eventList = listOf(
            EventModel(
                nama = "Kreatif Market Riau",
                tanggal = "12 - 14 Oktober 2026",
                lokasi = "Taman Labuai, Pekanbaru",
                harga = "Rp35.000",
                gambar = R.drawable.kreatif
            ),
            EventModel(
                nama = "Mini Concert Labuai",
                tanggal = "20 Oktober 2026",
                lokasi = "Pekanbaru",
                harga = "Rp45.000",
                gambar = R.drawable.miniconcert
            ),
            EventModel(
                nama = "Workshop Keramik",
                tanggal = "25 Oktober 2026",
                lokasi = "Studio Tanah",
                harga = "Rp85.000",
                gambar = R.drawable.keramik
            ),
            EventModel(
                nama = "Bazar Karya Lokal",
                tanggal = "1 November 2026",
                lokasi = "Lapangan Purna MTQ",
                harga = "Rp20.000",
                gambar = R.drawable.bazaar
            ),
            EventModel(
                nama = "Sore Akustik Lokal",
                tanggal = "31 Oktober 2026",
                lokasi = "Kedai Kala",
                harga = "Rp25.000",
                gambar = R.drawable.akustik
            ),
            EventModel(
                nama = "Lukis Tote Bag",
                tanggal = "1 November 2026",
                lokasi = "Ruang Rupa",
                harga = "Rp65.000",
                gambar = R.drawable.lukistotebag
            )
        )

        // Menghubungkan ListView dengan Custom Adapter
        val adapter = EventAdapter(
            requireContext(),
            eventList
        )

        binding.listEvent.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
