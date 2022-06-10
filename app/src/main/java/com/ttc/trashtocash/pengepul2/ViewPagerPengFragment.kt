package com.ttc.trashtocash.pengepul2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ttc.trashtocash.databinding.FragmentViewPagerPengBinding
import com.ttc.trashtocash.pengepul.MapsPengepulFragment

class ViewPagerPengFragment : Fragment(){
    private var _binding: FragmentViewPagerPengBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerPengBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            MapsPengepulFragment(),
            PengDijemputFragment(),
            SelesaiFragment(),
            PengBatalFragment()
        )

        val adapter = ViewPagerAdapterPeng(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.pengepulViewPager2.adapter = adapter

        return binding.root
    }
}