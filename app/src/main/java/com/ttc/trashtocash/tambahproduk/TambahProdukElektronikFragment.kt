package com.ttc.trashtocash.tambahproduk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.databinding.FragmentTambahProdukElektronikBinding


class TambahProdukElektronikFragment : Fragment() {

    val args: TambahProdukElektronikFragmentArgs by navArgs()
    var totalharga: String? = ""
    private var _binding: FragmentTambahProdukElektronikBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTambahProdukElektronikBinding.inflate(inflater, container, false)

        val jenispermintaan = args.jenispermintaan

        //berat elektronik
        var totalBerat = 0
        var strTotalBerat = ""
        var beratElektronik = binding.TEItemWeight.text.toString().toInt()

        //jenis elektronik
        val tv = binding.TETVCheckbox
        val kipas = binding.TEKipasanginCheckbox
        val ac = binding.TEACCheckbox
        val mesincuci = binding.TEMesincuciCheckbox
        val dispenser = binding.TEDispenserCheckbox
        val lainnya = binding.TEElektroniklainnyaCheckbox

        binding.TEPlus.setOnClickListener {
            totalBerat++
            binding.TEItemWeight.setText(totalBerat.toString())
            checkTotal()
        }
        binding.TEMinus.setOnClickListener {
            if(totalBerat>0){
                totalBerat--
                binding.TEItemWeight.setText(totalBerat.toString())
                checkTotal()
            }
        }

        binding.TEBackbtn.setOnClickListener {
            if(jenispermintaan == "tambahproduk"){
                val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                findNavController().navigate(action)

            }else if(jenispermintaan == "pickup"){
                val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                findNavController().navigate(action)
            }
        }

        binding.TEContinueCard.setOnClickListener {
            if (totalBerat != 0){
                if(jenispermintaan == "tambahproduk"){
                    if(tv.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "TV", "elektronik")
                        findNavController().navigate(action)
                    }else if(kipas.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Kipas Angin", "elektronik")
                        findNavController().navigate(action)
                    }else if(ac.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "AC", "elektronik")
                        findNavController().navigate(action)
                    }else if(mesincuci.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Mesin Cuci", "elektronik")
                        findNavController().navigate(action)
                    }else if(dispenser.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Dispenser", "elektronik")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "barang elektronik lainnya", "elektronik")
                        findNavController().navigate(action)
                    }
                }
                else if(jenispermintaan == "pickup"){
                    if(tv.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "TV", "elektronik")
                        findNavController().navigate(action)
                    }else if(kipas.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Kipas Angin", "elektronik")
                        findNavController().navigate(action)
                    }else if(ac.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "AC", "elektronik")
                        findNavController().navigate(action)
                    }else if(mesincuci.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Mesin Cuci", "elektronik")
                        findNavController().navigate(action)
                    }else if(dispenser.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Dispenser", "elektronik")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukElektronikFragmentDirections.actionTambahProdukElektronikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "barang elektronik lainnya", "elektronik")
                        findNavController().navigate(action)
                    }

                }

            }
        }

        tv.setOnClickListener{
            if (tv.isChecked){
                kipas.isChecked = false
                ac.isChecked = false
                mesincuci.isChecked = false
                dispenser.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!tv.isChecked){
                binding.TEEstimasiharga.setText("Rp. 0")
            }
        }

        kipas.setOnClickListener{
            if (kipas.isChecked){
                tv.isChecked = false
                ac.isChecked = false
                mesincuci.isChecked = false
                dispenser.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!kipas.isChecked){
                binding.TEEstimasiharga.setText("Rp. 0")
            }
        }

        ac.setOnClickListener{
            if (ac.isChecked){
                tv.isChecked = false
                kipas.isChecked = false
                mesincuci.isChecked = false
                dispenser.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!ac.isChecked){
                binding.TEEstimasiharga.setText("Rp. 0")
            }
        }

        mesincuci.setOnClickListener{
            if (mesincuci.isChecked){
                tv.isChecked = false
                kipas.isChecked = false
                ac.isChecked = false
                dispenser.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!mesincuci.isChecked){
                binding.TEEstimasiharga.setText("Rp. 0")
            }
        }

        dispenser.setOnClickListener{
            if (dispenser.isChecked){
                tv.isChecked = false
                kipas.isChecked = false
                ac.isChecked = false
                mesincuci.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!dispenser.isChecked){
                binding.TEEstimasiharga.setText("Rp. 0")
            }
        }

        lainnya.setOnClickListener{
            if (lainnya.isChecked){
                tv.isChecked = false
                kipas.isChecked = false
                ac.isChecked = false
                mesincuci.isChecked = false
                dispenser.isChecked = false
                checkTotal()

            }else if(!lainnya.isChecked){
                binding.TEEstimasiharga.setText("Rp. 0")
            }
        }

        return binding.root
    }

    private fun checkTotal(){

        //berat elektronik
        var totalBerat = 0
        var beratElektronik = binding.TEItemWeight.text.toString().toInt()

        //jenis elektronik
        val tv = binding.TETVCheckbox
        val kipas = binding.TEKipasanginCheckbox
        val ac = binding.TEACCheckbox
        val mesincuci = binding.TEMesincuciCheckbox
        val dispenser = binding.TEDispenserCheckbox
        val lainnya = binding.TEElektroniklainnyaCheckbox

        if (tv.isChecked){
            binding.TEEstimasiharga.setText("Rp. ${beratElektronik*10000} sd Rp. ${beratElektronik*100000}")
            totalharga = (beratElektronik*60000).toString()
        }
        if(kipas.isChecked){
            binding.TEEstimasiharga.setText("Rp. ${beratElektronik*3000} sd Rp. ${beratElektronik*15000}")
            totalharga = (beratElektronik*10000).toString()
        }
        if(ac.isChecked){
            binding.TEEstimasiharga.setText("Rp. ${beratElektronik*50000} sd Rp. ${beratElektronik*200000}")
            totalharga = (beratElektronik*120000).toString()
        }
        if(mesincuci.isChecked){
            binding.TEEstimasiharga.setText("Rp. ${beratElektronik*30000} sd Rp. ${beratElektronik*100000}")
            totalharga = (beratElektronik*50000).toString()
        }
        if(dispenser.isChecked){
            binding.TEEstimasiharga.setText("Rp. ${beratElektronik*3000} sd Rp. ${beratElektronik*40000}")
            totalharga = (beratElektronik*20000).toString()
        }
        if(lainnya.isChecked){
            binding.TEEstimasiharga.setText("Rp. ${beratElektronik*5000} sd Rp. ${beratElektronik*30000}")
            totalharga = (beratElektronik*25000).toString()
        }
    }

}