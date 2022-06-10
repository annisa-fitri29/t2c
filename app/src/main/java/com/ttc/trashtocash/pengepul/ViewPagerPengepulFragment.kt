package com.ttc.trashtocash.pengepul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ttc.trashtocash.databinding.FragmentViewPagerPengepulBinding


class ViewPagerPengepulFragment : Fragment() {

    private var _binding: FragmentViewPagerPengepulBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerPengepulBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            DijemputPengepulFragment(),
            SelesaiPengepulFragment(),
            DibatalkanPengepulFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.pengepulViewPager.adapter = adapter

        return binding.root
    }


}