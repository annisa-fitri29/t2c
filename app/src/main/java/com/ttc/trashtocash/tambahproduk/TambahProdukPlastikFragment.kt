package com.ttc.trashtocash.tambahproduk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.databinding.FragmentTambahProdukPlastikBinding


class TambahProdukPlastikFragment : Fragment() {
    val args: TambahProdukPlastikFragmentArgs by navArgs()
    var totalharga: String? = ""
    private var _binding: FragmentTambahProdukPlastikBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTambahProdukPlastikBinding.inflate(inflater, container, false)

        val jenispermintaan = args.jenispermintaan

        //berat plastik
        var totalBerat = 0
        var strTotalBerat = ""
        var beratPlastik = binding.TPLItemWeight.text.toString().toInt()

        //jenis plastik
        val gelas = binding.TPLPlastikgelasCheckbox
        val botol = binding.TPLBotolplastikCheckbox
        val HD = binding.TPLHDCheckbox
        val HDblow = binding.TPLHDblowCheckbox
        val damar = binding.TPLPlastikdamarCheckbox
        val lainnya = binding.TPLPlastiklainnyaCheckbox

        binding.TPLPlus.setOnClickListener {
            totalBerat++
            binding.TPLItemWeight.setText(totalBerat.toString())
            checkTotal()
        }
        binding.TPLMinus.setOnClickListener {
            if(totalBerat>0){
                totalBerat--
                binding.TPLItemWeight.setText(totalBerat.toString())
                checkTotal()
            }
        }

        binding.TPLBackbtn.setOnClickListener {
            if(jenispermintaan == "tambahproduk"){
                val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                findNavController().navigate(action)

            }else if(jenispermintaan == "pickup"){
                val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                findNavController().navigate(action)
            }
        }

        binding.TPLContinueCard.setOnClickListener {
            if (totalBerat != 0){
                if(jenispermintaan == "tambahproduk"){
                    if(gelas.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                        findNavController().navigate(action)
                    }else if(botol.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol Plastik", "plastik")
                        findNavController().navigate(action)
                    }else if(HD.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "HD", "plastik")
                        findNavController().navigate(action)
                    }else if(HDblow.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "HD Blow", "plastik")
                        findNavController().navigate(action)
                    }else if(damar.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Damar", "plastik")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Lainnya", "plastik")
                        findNavController().navigate(action)
                    }
                }
                else if(jenispermintaan == "pickup"){

                    if(gelas.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                        findNavController().navigate(action)
                    }else if(botol.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol Plastik", "plastik")
                        findNavController().navigate(action)
                    }else if(HD.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "HD", "plastik")
                        findNavController().navigate(action)
                    }else if(HDblow.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "HD Blow", "plastik")
                        findNavController().navigate(action)
                    }else if(damar.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Damar", "plastik")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukPlastikFragmentDirections.actionTambahProdukPlastikFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Lainnya", "plastik")
                        findNavController().navigate(action)
                    }
                }
            }
        }

        gelas.setOnClickListener{
            if (gelas.isChecked){
                botol.isChecked = false
                HD.isChecked = false
                HDblow.isChecked = false
                damar.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!gelas.isChecked){
                binding.TPLEstimasiharga.setText("Rp. 0")
            }
        }

        botol.setOnClickListener{
            if (botol.isChecked){
                gelas.isChecked = false
                HD.isChecked = false
                HDblow.isChecked = false
                damar.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!botol.isChecked){
                binding.TPLEstimasiharga.setText("Rp. 0")
            }
        }

        HD.setOnClickListener{
            if (HD.isChecked){
                gelas.isChecked = false
                botol.isChecked = false
                HDblow.isChecked = false
                damar.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!HD.isChecked){
                binding.TPLEstimasiharga.setText("Rp. 0")
            }
        }

        HDblow.setOnClickListener{
            if (HDblow.isChecked){
                gelas.isChecked = false
                botol.isChecked = false
                HD.isChecked = false
                damar.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!HDblow.isChecked){
                binding.TPLEstimasiharga.setText("Rp. 0")
            }
        }

        damar.setOnClickListener{
            if (damar.isChecked){
                gelas.isChecked = false
                botol.isChecked = false
                HD.isChecked = false
                HDblow.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!damar.isChecked){
                binding.TPLEstimasiharga.setText("Rp. 0")
            }
        }

        lainnya.setOnClickListener{
            if (lainnya.isChecked){
                gelas.isChecked = false
                botol.isChecked = false
                HD.isChecked = false
                HDblow.isChecked = false
                damar.isChecked = false
                checkTotal()

            }else if(!lainnya.isChecked){
                binding.TPLEstimasiharga.setText("Rp. 0")
            }
        }


        return binding.root
    }


    private fun checkTotal(){

        //berat plastik
        var totalBerat = 0
        var beratPlastik = binding.TPLItemWeight.text.toString().toInt()

        //jenis plastik
        val gelas = binding.TPLPlastikgelasCheckbox
        val botol = binding.TPLBotolplastikCheckbox
        val HD = binding.TPLHDCheckbox
        val HDblow = binding.TPLHDblowCheckbox
        val damar = binding.TPLPlastikdamarCheckbox
        val lainnya = binding.TPLPlastiklainnyaCheckbox

        if (gelas.isChecked){
            binding.TPLEstimasiharga.setText("Rp. ${beratPlastik*1500} sd Rp. ${beratPlastik*2500}")
            totalharga = (beratPlastik*2000).toString()
        }
        if(botol.isChecked){
            binding.TPLEstimasiharga.setText("Rp. ${beratPlastik*1000} sd Rp. ${beratPlastik*1500}")
            totalharga = (beratPlastik*1000).toString()
        }
        if(HD.isChecked){
            binding.TPLEstimasiharga.setText("Rp. ${beratPlastik*2000} sd Rp. ${beratPlastik*2700}")
            totalharga = (beratPlastik*2500).toString()
        }
        if(HDblow.isChecked){
            binding.TPLEstimasiharga.setText("Rp. ${beratPlastik*2500} sd Rp. ${beratPlastik*2700}")
            totalharga = (beratPlastik*2500).toString()
        }
        if(damar.isChecked){
            binding.TPLEstimasiharga.setText("Rp. ${beratPlastik*500} sd Rp. ${beratPlastik*1500}")
            totalharga = (beratPlastik*1000).toString()
        }
        if(lainnya.isChecked){
            binding.TPLEstimasiharga.setText("Rp. ${beratPlastik*1000} sd Rp. ${beratPlastik*1500}")
            totalharga = (beratPlastik*1500).toString()
        }
    }

}