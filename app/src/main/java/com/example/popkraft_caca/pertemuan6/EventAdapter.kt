package com.example.popkraft_caca.pertemuan6

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.popkraft_caca.databinding.ItemEventBinding
import com.example.popkraft_caca.pertemuan5.LimaActivity

class EventAdapter(
    context: Context,
    private val eventList: List<EventModel>
) : ArrayAdapter<EventModel>(context, 0, eventList) {

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val binding: ItemEventBinding

        // Menggunakan kembali view agar lebih efisien
        if (convertView == null) {
            binding = ItemEventBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
            binding.root.tag = binding
        } else {
            binding = convertView.tag as ItemEventBinding
        }

        val event = eventList[position]

        // Menampilkan data event
        binding.txtNamaEvent.text = event.nama
        binding.txtTanggalEvent.text = event.tanggal
        binding.txtLokasiEvent.text = event.lokasi
        binding.txtHargaEvent.text =
            "${event.harga} / orang"

        // Menampilkan gambar menggunakan Glide
        Glide.with(binding.root)
            .load(event.gambar)
            .into(binding.imgEvent)

        // Tombol untuk membuka Detail Event
        val bukaDetail = View.OnClickListener {

            val intent = Intent(
                context,
                LimaActivity::class.java
            )

            intent.putExtra("nama_event", event.nama)
            intent.putExtra("tanggal_event", event.tanggal)
            intent.putExtra("lokasi_event", event.lokasi)
            intent.putExtra("harga_event", event.harga)
            intent.putExtra("gambar_event", event.gambar)

            context.startActivity(intent)
        }

        binding.btnLihatDetail.setOnClickListener(bukaDetail)

        // Seluruh kartu juga bisa diklik
        binding.root.setOnClickListener(bukaDetail)

        return binding.root
    }
}
