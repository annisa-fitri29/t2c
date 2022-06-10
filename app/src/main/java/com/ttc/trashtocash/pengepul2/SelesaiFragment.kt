package com.ttc.trashtocash.pengepul2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.ttc.trashtocash.R
import com.ttc.trashtocash.databinding.FragmentSelesaiBinding
import com.ttc.trashtocash.databinding.FragmentSelesaiPengepulBinding


class SelesaiFragment : Fragment() {

    private var _binding: FragmentSelesaiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelesaiBinding.inflate(inflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.pengepulViewPager2)

        binding.sPPengepulDikemasbtn.setOnClickListener {
            viewPager?.currentItem = 1
        }

        binding.sPPengepulDibatalkanbtn.setOnClickListener {
            viewPager?.currentItem = 3
        }

        return binding.root
    }


}