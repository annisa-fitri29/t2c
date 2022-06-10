package com.ttc.trashtocash.tambahproduk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.databinding.FragmentTambahProdukBesiBinding


class TambahProdukBesiFragment : Fragment() {

    val args: TambahProdukBesiFragmentArgs by navArgs()
    var totalharga: String? = ""
    private var _binding: FragmentTambahProdukBesiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentTambahProdukBesiBinding.inflate(inflater, container, false)

        val jenispermintaan = args.jenispermintaan

        //berat besi
        var totalBerat = 0
        var strTotalBerat = ""
        var beratBesi = binding.TBSItemWeight.text.toString().toInt()

        //jenis besi
        val aki = binding.TBSAkiCheckbox
        val timah = binding.TBSTimahCheckbox
        val tembaga = binding.TBSTembagabiasaCheckbox
        val tipis = binding.TBSBesitipisCheckbox
        val kuningan = binding.TBSKuninganCheckbox
        val lainnya = binding.TBSBesilainnyaCheckbox

        binding.TBSPlus.setOnClickListener {
            totalBerat++
            binding.TBSItemWeight.setText(totalBerat.toString())
            checkTotal()
        }
        binding.TBSMinus.setOnClickListener {
            if(totalBerat>0){
                totalBerat--
                binding.TBSItemWeight.setText(totalBerat.toString())
                checkTotal()
            }
        }

        if(jenispermintaan == "tambahproduk"){
            val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
            findNavController().navigate(action)

        }else if(jenispermintaan == "pickup"){
            val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
            findNavController().navigate(action)
        }

        binding.TBSContinueCard.setOnClickListener {
            if (totalBerat != 0){
                if(jenispermintaan == "tambahproduk"){
                    if(aki.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Aki", "besi")
                        findNavController().navigate(action)
                    }else if(timah.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Timah", "besi")
                        findNavController().navigate(action)
                    }else if(tembaga.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Tembaga", "besi")
                        findNavController().navigate(action)
                    }else if(tipis.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Besi Tipis", "besi")
                        findNavController().navigate(action)
                    }else if(kuningan.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Kuningan", "besi")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Besi Lainnya", "besi")
                        findNavController().navigate(action)
                    }
                }
                else if(jenispermintaan == "pickup"){

                    if(aki.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Aki", "besi")
                        findNavController().navigate(action)
                    }else if(timah.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Timah", "besi")
                        findNavController().navigate(action)
                    }else if(tembaga.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Tembaga", "besi")
                        findNavController().navigate(action)
                    }else if(tipis.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Besi Tipis", "besi")
                        findNavController().navigate(action)
                    }else if(kuningan.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Kuningan", "besi")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Besi Lainnya", "besi")
                        findNavController().navigate(action)
                    }
                }

            }
        }


        aki.setOnClickListener{
            if (aki.isChecked){
                timah.isChecked = false
                tembaga.isChecked = false
                tipis.isChecked = false
                kuningan.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!aki.isChecked){
                binding.TBSEstimasiharga.setText("Rp. 0")
            }
        }

        timah.setOnClickListener{
            if (timah.isChecked){
                aki.isChecked = false
                tembaga.isChecked = false
                tipis.isChecked = false
                kuningan.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!timah.isChecked){
                binding.TBSEstimasiharga.setText("Rp. 0")
            }
        }

        tembaga.setOnClickListener{
            if (tembaga.isChecked){
                aki.isChecked = false
                timah.isChecked = false
                tipis.isChecked = false
                kuningan.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!tembaga.isChecked){
                binding.TBSEstimasiharga.setText("Rp. 0")
            }
        }

        tipis.setOnClickListener{
            if (tipis.isChecked){
                aki.isChecked = false
                timah.isChecked = false
                tembaga.isChecked = false
                kuningan.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!tipis.isChecked){
                binding.TBSEstimasiharga.setText("Rp. 0")
            }
        }

        kuningan.setOnClickListener{
            if (kuningan.isChecked){
                aki.isChecked = false
                timah.isChecked = false
                tembaga.isChecked = false
                tipis.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!kuningan.isChecked){
                binding.TBSEstimasiharga.setText("Rp. 0")
            }
        }

        lainnya.setOnClickListener{
            if (lainnya.isChecked){
                aki.isChecked = false
                timah.isChecked = false
                tembaga.isChecked = false
                tipis.isChecked = false
                kuningan.isChecked = false
                checkTotal()

            }else if(!lainnya.isChecked){
                binding.TBSEstimasiharga.setText("Rp. 0")
            }
        }

        return binding.root
    }

    private fun checkTotal(){

        //berat besi
        var totalBerat = 0
        var beratBesi = binding.TBSItemWeight.text.toString().toInt()

        //jenis besi
        val aki = binding.TBSAkiCheckbox
        val timah = binding.TBSTimahCheckbox
        val tembaga = binding.TBSTembagabiasaCheckbox
        val tipis = binding.TBSBesitipisCheckbox
        val kuningan = binding.TBSKuninganCheckbox
        val lainnya = binding.TBSBesilainnyaCheckbox

        if (aki.isChecked){
            binding.TBSEstimasiharga.setText("Rp. ${beratBesi*10000} sd Rp. ${beratBesi*25000}")
            totalharga = (beratBesi*20000).toString()
        }
        if(timah.isChecked){
            binding.TBSEstimasiharga.setText("Rp. ${beratBesi*10000} sd Rp. ${beratBesi*13000}")
            totalharga = (beratBesi*11000).toString()
        }
        if(tembaga.isChecked){
            binding.TBSEstimasiharga.setText("Rp. ${beratBesi*45000} sd Rp. ${beratBesi*50000}")
            totalharga = (beratBesi*48000).toString()
        }
        if(tipis.isChecked){
            binding.TBSEstimasiharga.setText("Rp. ${beratBesi*1000} sd Rp. ${beratBesi*1500}")
            totalharga = (beratBesi*1000).toString()
        }
        if(kuningan.isChecked){
            binding.TBSEstimasiharga.setText("Rp. ${beratBesi*25000} sd Rp. ${beratBesi*30000}")
            totalharga = (beratBesi*27000).toString()
        }
        if(lainnya.isChecked){
            binding.TBSEstimasiharga.setText("Rp. ${beratBesi*3000} sd Rp. ${beratBesi*8000}")
            totalharga = (beratBesi*5000).toString()
        }
    }

}