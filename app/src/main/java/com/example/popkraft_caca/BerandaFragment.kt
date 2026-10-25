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

    // Menerima pilihan kategori dari KategoriActivity
    private val pilihKategoriLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        if (result.resultCode == Activity.RESULT_OK) {
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

        if (savedInstanceState != null) {
            kategoriAktif = savedInstanceState.getInt(
                "kategori_aktif",
                0
            )
        }

        if (childFragmentManager.findFragmentById(
                binding.fragmentContainer.id
            ) == null
        ) {
            tampilkanKategori(kategoriAktif)
        } else {
            ubahWarnaKategori(kategoriAktif)
        }

        binding.btnSemua.setOnClickListener {
            tampilkanKategori(0)
        }

        binding.btnMarket.setOnClickListener {
            tampilkanKategori(1)
        }

        binding.btnWorkshop.setOnClickListener {
            tampilkanKategori(2)
        }

        binding.btnMusik.setOnClickListener {
            tampilkanKategori(3)
        }

        // Membuka halaman ListView kategori
        binding.txtLihatSemua.setOnClickListener {
            val intent = Intent(
                requireContext(),
                KategoriActivity::class.java
            )

            pilihKategoriLauncher.launch(intent)
        }

        // Logout
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

    private fun tampilkanKategori(pilihan: Int) {

        kategoriAktif = pilihan

        val fragment = when (pilihan) {
            1 -> DuaFragment()
            2 -> TigaFragment()
            3 -> EmpatFragment()
            else -> SatuFragment()
        }

        childFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()

        ubahWarnaKategori(pilihan)
    }

    private fun ubahWarnaKategori(pilihan: Int) {

        val tombol = listOf(
            binding.btnSemua,
            binding.btnMarket,
            binding.btnWorkshop,
            binding.btnMusik
        )

        tombol.forEachIndexed { index, button ->

            val backgroundColor = if (index == pilihan) {
                R.color.purple
            } else {
                R.color.light_purple
            }

            val textColor = if (index == pilihan) {
                R.color.white
            } else {
                R.color.dark_purple
            }

            button.backgroundTintList =
                ColorStateList.valueOf(
                    requireContext().getColor(backgroundColor)
                )

            button.setTextColor(
                requireContext().getColor(textColor)
            )
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("kategori_aktif", kategoriAktif)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
