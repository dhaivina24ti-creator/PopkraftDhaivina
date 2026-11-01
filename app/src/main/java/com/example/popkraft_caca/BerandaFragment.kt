package com.example.popkraft_caca

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.databinding.FragmentBerandaBinding
import com.example.popkraft_caca.pertemuan6.SatuFragment
import com.example.popkraft_caca.pertemuan6.DuaFragment
import com.example.popkraft_caca.pertemuan6.TigaFragment
import com.example.popkraft_caca.pertemuan6.EmpatFragment

class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    private var kategoriAktif = 0

    // Menerima pilihan dari halaman Kategori Event
    private val kategoriLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK && _binding != null) {

            val kategori = result.data
                ?.getStringExtra("kategori_dipilih")

            when (kategori) {
                "Pop-up Market" -> tampilkanKategori(1)
                "Workshop" -> tampilkanKategori(2)
                "Musik" -> tampilkanKategori(3)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBerandaBinding.inflate(
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

        // Mengembalikan kategori yang terakhir aktif
        kategoriAktif = savedInstanceState?.getInt(
            "kategori_aktif",
            kategoriAktif
        ) ?: kategoriAktif

        // Menampilkan event berdasarkan kategori aktif
        tampilkanKategori(kategoriAktif)

        // =====================================
        // BARU: MEMBUKA HALAMAN PENCARIAN EVENT
        // =====================================
        binding.edtSearch.setOnClickListener {

            val intent = Intent(
                requireContext(),
                CariEventActivity::class.java
            )

            startActivity(intent)
        }


        // CHIP GROUP FILTER KATEGORI
        binding.chipGroupKategori
            .setOnCheckedStateChangeListener { _, checkedIds ->
                when (checkedIds.firstOrNull()) {
                    R.id.chipSemua -> {
                        tampilkanKategori(0)
                    }
                    R.id.chipMarket -> {
                        tampilkanKategori(1)
                    }
                    R.id.chipWorkshop -> {
                        tampilkanKategori(2)
                    }
                    R.id.chipMusik -> {
                        tampilkanKategori(3)
                    }
                }
            }

        // =====================================
        // MEMBUKA HALAMAN SEMUA KATEGORI
        // =====================================
        binding.txtLihatSemua.setOnClickListener {

            val intent = Intent(
                requireContext(),
                KategoriActivity::class.java
            )

            kategoriLauncher.launch(intent)
        }

        // =====================================
        // TOMBOL LOGOUT
        // =====================================
        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Yakin ingin keluar dari PopKraf?")
                .setPositiveButton("Ya") { _, _ ->

                    val pref = requireContext()
                        .getSharedPreferences(
                            "user_pref",
                            Context.MODE_PRIVATE
                        )

                    // Menghapus status login
                    pref.edit().clear().apply()

                    val intent = Intent(
                        requireContext(),
                        LoginActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    // =====================================
    // MENAMPILKAN FRAGMENT BERDASARKAN KATEGORI
    // =====================================
    private fun tampilkanKategori(pilihan: Int) {

        val chipId = when (pilihan) {
            1 -> R.id.chipMarket
            2 -> R.id.chipWorkshop
            3 -> R.id.chipMusik
            else -> R.id.chipSemua
        }

        // Menandai Chip yang sedang dipilih
        if (binding.chipGroupKategori.checkedChipId != chipId) {
            binding.chipGroupKategori.check(chipId)
        }

        // Mengubah warna Chip aktif
        ubahWarnaChip(pilihan)

        // Periksa Fragment yang sekarang ditampilkan
        val fragmentLama = childFragmentManager
            .findFragmentById(binding.fragmentContainer.id)

        // Jangan mengganti Fragment jika sudah sesuai
        if (kategoriAktif == pilihan && fragmentLama != null) {
            return
        }

        kategoriAktif = pilihan

        val fragment = when (pilihan) {

            1 -> DuaFragment()

            2 -> TigaFragment()

            3 -> EmpatFragment()

            else -> SatuFragment()
        }

        // Mengganti daftar event pada Beranda
        childFragmentManager.beginTransaction()
            .replace(
                binding.fragmentContainer.id,
                fragment
            )
            .commit()
    }

    // =====================================
    // MENGUBAH WARNA CHIP KATEGORI
    // =====================================
    private fun ubahWarnaChip(pilihan: Int) {

        val daftarChip = listOf(
            binding.chipSemua,
            binding.chipMarket,
            binding.chipWorkshop,
            binding.chipMusik
        )

        daftarChip.forEachIndexed { index, chip ->

            val warnaBackground =
                if (index == pilihan) {
                    R.color.purple
                } else {
                    R.color.light_purple
                }

            val warnaTeks =
                if (index == pilihan) {
                    R.color.white
                } else {
                    R.color.dark_purple
                }

            chip.chipBackgroundColor =
                ColorStateList.valueOf(
                    requireContext().getColor(warnaBackground)
                )

            chip.setTextColor(
                requireContext().getColor(warnaTeks)
            )
        }
    }

    // Menyimpan kategori saat layar dibuat ulang
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("kategori_aktif", kategoriAktif)
        super.onSaveInstanceState(outState)
    }

    // Membersihkan View Binding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
