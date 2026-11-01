package com.example.popkraft_caca

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Tutorial1Fragment()
            1 -> TutorialFeatureFragment.newInstance(2)
            2 -> TutorialFeatureFragment.newInstance(3)
            else -> TutorialFeatureFragment.newInstance(4)
        }
    }
}
