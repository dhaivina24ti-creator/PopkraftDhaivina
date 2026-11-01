
package com.example.popkraft_caca

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.popkraft_caca.databinding.FragmentTutorialFeatureBinding

class TutorialFeatureFragment : Fragment() {

    private var _binding: FragmentTutorialFeatureBinding? = null
    private val binding get() = _binding!!

    companion object {

        fun newInstance(slide: Int): TutorialFeatureFragment {
            return TutorialFeatureFragment().apply {
                arguments = Bundle().apply {
                    putInt("slide", slide)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTutorialFeatureBinding.inflate(
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

        when (requireArguments().getInt("slide")) {

            // SLIDE 2 - EKSPLOR EVENT
            2 -> {
                binding.txtJudulTutorial.text =
                    "Jelajahi Event Kreatif"

                binding.txtDeskripsiTutorial.text =
                    "Temukan pop-up market, workshop, konser musik, " +
                            "dan pameran seni dari kreator lokal."

                binding.imgTutorial.setImageResource(
                    R.drawable.kreatif
                )
            }

            // SLIDE 3 - SEKITARKU
            3 -> {
                binding.txtJudulTutorial.text =
                    "Temukan Event di Sekitarmu"

                binding.txtDeskripsiTutorial.text =
                    "Kenali berbagai kegiatan ekonomi kreatif " +
                            "di sekitar lokasi kamu dan jelajahi " +
                            "pengalaman baru yang menarik."

                binding.imgTutorial.setImageResource(
                    R.drawable.pasar
                )
            }

            // SLIDE 4 - TIKET DIGITAL
            4 -> {
                binding.txtJudulTutorial.text =
                    "Kenali Fitur Tiket Digital"

                binding.txtDeskripsiTutorial.text =
                    "PopKraf dirancang untuk memudahkan " +
                            "pemesanan dan penyimpanan tiket event " +
                            "secara digital."

                binding.imgTutorial.setImageResource(
                    R.drawable.miniconcert
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
