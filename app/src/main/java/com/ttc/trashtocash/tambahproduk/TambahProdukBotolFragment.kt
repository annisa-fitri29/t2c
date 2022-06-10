package com.ttc.trashtocash.tambahproduk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.databinding.FragmentTambahProdukBotolBinding

class TambahProdukBotolFragment : Fragment() {

    val args: TambahProdukBotolFragmentArgs by navArgs()
    var totalharga: String? = ""
    private var _binding: FragmentTambahProdukBotolBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentTambahProdukBotolBinding.inflate(inflater, container, false)

        val jenispermintaan = args.jenispermintaan

        //berat botol
        var totalBerat = 0
        var strTotalBerat = ""
        var beratBotol = binding.TBItemWeight.text.toString().toInt()

        //jenis botol
        val saos = binding.TBBotolsaosCheckbox
        val sirupdht = binding.TBBotolsirupdhtCheckbox
        val mirasbesar = binding.TBBotolminumankerasbesarCheckbox
        val miraskecil = binding.TBBotolminumankeraskecilCheckbox
        val kecap = binding.TBBotolkecapCheckbox
        val lainnya = binding.TBBotolkacalainnyaCheckbox

        binding.TBPlus.setOnClickListener {
            totalBerat++
            binding.TBItemWeight.setText(totalBerat.toString())
            checkTotal()
        }
        binding.TBMinus.setOnClickListener {
            if(totalBerat>0){
                totalBerat--
                binding.TBItemWeight.setText(totalBerat.toString())
                checkTotal()
            }
        }

        if(jenispermintaan == "tambahproduk"){
            val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
            findNavController().navigate(action)

        }else if(jenispermintaan == "pickup"){
            val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
            findNavController().navigate(action)
        }

        binding.TBContinueCard.setOnClickListener {
            if (totalBerat != 0){
                if(jenispermintaan == "tambahproduk"){
                    if(saos.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol saos", "botol")
                        findNavController().navigate(action)
                    }else if(sirupdht.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol Sirup DHT", "botol")
                        findNavController().navigate(action)
                    }else if(mirasbesar.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol miras besar", "botol")
                        findNavController().navigate(action)
                    }else if(miraskecil.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol miras kecil", "botol")
                        findNavController().navigate(action)
                    }else if(kecap.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol Kecap", "botol")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBesiFragmentDirections.actionTambahProdukBesiFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Botol kaca Lainnya", "botol")
                        findNavController().navigate(action)
                    }
                }
                else if(jenispermintaan == "pickup"){

                    if(saos.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol saos", "botol")
                        findNavController().navigate(action)
                    }else if(sirupdht.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol Sirup DHT", "botol")
                        findNavController().navigate(action)
                    }else if(mirasbesar.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol miras besar", "botol")
                        findNavController().navigate(action)
                    }else if(miraskecil.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol miras kecil", "botol")
                        findNavController().navigate(action)
                    }else if(kecap.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol Kecap", "botol")
                        findNavController().navigate(action)
                    }else if(lainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = TambahProdukBotolFragmentDirections.actionTambahProdukBotolFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Botol kaca Lainnya", "botol")
                        findNavController().navigate(action)
                    }
                }

            }
        }

        saos.setOnClickListener{
            if (saos.isChecked){
                sirupdht.isChecked = false
                mirasbesar.isChecked = false
                miraskecil.isChecked = false
                kecap.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!saos.isChecked){
                binding.TBEstimasiharga.setText("Rp. 0")
            }
        }

        sirupdht.setOnClickListener{
            if (sirupdht.isChecked){
                saos.isChecked = false
                mirasbesar.isChecked = false
                miraskecil.isChecked = false
                kecap.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!sirupdht.isChecked){
                binding.TBEstimasiharga.setText("Rp. 0")
            }
        }

        mirasbesar.setOnClickListener{
            if (mirasbesar.isChecked){
                saos.isChecked = false
                sirupdht.isChecked = false
                miraskecil.isChecked = false
                kecap.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!mirasbesar.isChecked){
                binding.TBEstimasiharga.setText("Rp. 0")
            }
        }

        miraskecil.setOnClickListener{
            if (miraskecil.isChecked){
                saos.isChecked = false
                sirupdht.isChecked = false
                mirasbesar.isChecked = false
                kecap.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!miraskecil.isChecked){
                binding.TBEstimasiharga.setText("Rp. 0")
            }
        }

        kecap.setOnClickListener{
            if (kecap.isChecked){
                saos.isChecked = false
                sirupdht.isChecked = false
                mirasbesar.isChecked = false
                miraskecil.isChecked = false
                lainnya.isChecked = false
                checkTotal()

            }else if(!kecap.isChecked){
                binding.TBEstimasiharga.setText("Rp. 0")
            }
        }

        lainnya.setOnClickListener{
            if (lainnya.isChecked){
                saos.isChecked = false
                sirupdht.isChecked = false
                mirasbesar.isChecked = false
                miraskecil.isChecked = false
                kecap.isChecked = false
                checkTotal()

            }else if(!lainnya.isChecked){
                binding.TBEstimasiharga.setText("Rp. 0")
            }
        }

        return binding.root
    }



    private fun checkTotal(){

        //berat besi
        var totalBerat = 0
        var beratBotol = binding.TBItemWeight.text.toString().toInt()

        //jenis botol
        val saos = binding.TBBotolsaosCheckbox
        val sirupdht = binding.TBBotolsirupdhtCheckbox
        val mirasbesar = binding.TBBotolminumankerasbesarCheckbox
        val miraskecil = binding.TBBotolminumankeraskecilCheckbox
        val kecap = binding.TBBotolkecapCheckbox
        val lainnya = binding.TBBotolkacalainnyaCheckbox

        if (saos.isChecked){
            binding.TBEstimasiharga.setText("Rp. ${beratBotol*300} sd Rp. ${beratBotol*500}")
            totalharga = (beratBotol*400).toString()
        }
        if(sirupdht.isChecked){
            binding.TBEstimasiharga.setText("Rp. ${beratBotol*300} sd Rp. ${beratBotol*500}")
            totalharga = (beratBotol*400).toString()
        }
        if(mirasbesar.isChecked){
            binding.TBEstimasiharga.setText("Rp. ${beratBotol*500} sd Rp. ${beratBotol*1000}")
            totalharga = (beratBotol*800).toString()
        }
        if(miraskecil.isChecked){
            binding.TBEstimasiharga.setText("Rp. ${beratBotol*50} sd Rp. ${beratBotol*300}")
            totalharga = (beratBotol*100).toString()
        }
        if(kecap.isChecked){
            binding.TBEstimasiharga.setText("Rp. ${beratBotol*300} sd Rp. ${beratBotol*500}")
            totalharga = (beratBotol*400).toString()
        }
        if(lainnya.isChecked){
            binding.TBEstimasiharga.setText("Rp. ${beratBotol*300} sd Rp. ${beratBotol*500}")
            totalharga = (beratBotol*400).toString()
        }
    }
}