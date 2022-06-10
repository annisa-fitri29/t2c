package com.ttc.trashtocash.pengepul

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.FragmentSelesaiPengepulBinding


class SelesaiPengepulFragment : Fragment() {

    private var _binding: FragmentSelesaiPengepulBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelesaiPengepulBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.pengepulViewPager)

        binding.sPengepulDikemasbtn.setOnClickListener {
            viewPager?.currentItem = 0
        }

        binding.sPengepulDibatalkanbtn.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return binding.root
    }

}