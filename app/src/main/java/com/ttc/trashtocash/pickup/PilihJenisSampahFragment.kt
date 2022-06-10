package com.ttc.trashtocash.pickup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ttc.trashtocash.databinding.FragmentPilihJenisSampahBinding
import com.ttc.trashtocash.tambahproduk.TambahProdukElektronikFragmentDirections

class PilihJenisSampahFragment : Fragment() {

    val args: PilihJenisSampahFragmentArgs by navArgs()
    var totalharga: String? = ""

    private var _binding: FragmentPilihJenisSampahBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPilihJenisSampahBinding.inflate(inflater, container, false)

        val jenispermintaan = args.jenispermintaan
        var strTotalBerat = ""

        //jenis kertas
        val hvs = binding.jsHvsCheckbox
        val karton = binding.jsKartonCheckbox
        val koran = binding.jsKoranCheckbox
        val buku = binding.jsBukuCheckbox
        val bungkusRokok = binding.jsBungkusrokokCheckbox
        val kertasLainnya = binding.jsKertaslainnyaCheckbox

        //berat kertas
        var totalBerat = 0
        var beratKertas = binding.jsItemWeight.text.toString().toInt()

        binding.jsPlus.setOnClickListener {
            totalBerat++
            binding.jsItemWeight.setText(totalBerat.toString())
            checkTotal()
        }
        binding.jsMinus.setOnClickListener {
            if(totalBerat>0){
                totalBerat--
                binding.jsItemWeight.setText(totalBerat.toString())
                checkTotal()
            }
        }

        binding.jsBackbtn.setOnClickListener {
            if(jenispermintaan == "tambahproduk"){
                val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                findNavController().navigate(action)

            }else if(jenispermintaan == "pickup"){
                val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat, "Plastik Gelas", "plastik")
                findNavController().navigate(action)
            }
        }

        binding.jsContinueCard.setOnClickListener {
            if (totalBerat != 0){
                if(args.jenispermintaan == "tambahproduk"){
                    if(hvs.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat,"Kertas HVS","kertas")
                        findNavController().navigate(action)
                    }else if(karton.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat,"Kertas Karton","kertas")
                        findNavController().navigate(action)
                    }else if(buku.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat,"Buku Bekas","kertas")
                        findNavController().navigate(action)
                    }else if(koran.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat,"Koran","kertas")
                        findNavController().navigate(action)
                    }else if(bungkusRokok.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat,"Bungkus Rokok","kertas")
                        findNavController().navigate(action)
                    }else if(kertasLainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToTambahProdukFragment(totalharga!!,strTotalBerat,"Kertas Lainnya","kertas")
                        findNavController().navigate(action)
                    }
                }
                else if(args.jenispermintaan == "pickup"){
                    if(hvs.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat,"Kertas HVS","kertas")
                        findNavController().navigate(action)
                    }else if(karton.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat,"Kertas Karton","kertas")
                        findNavController().navigate(action)
                    }else if(buku.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat,"Buku Bekas","kertas")
                        findNavController().navigate(action)
                    }else if(koran.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat,"Koran","kertas")
                        findNavController().navigate(action)
                    }else if(bungkusRokok.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat,"Bungkus Rokok","kertas")
                        findNavController().navigate(action)
                    }else if(kertasLainnya.isChecked){
                        strTotalBerat = totalBerat.toString()
                        val action = PilihJenisSampahFragmentDirections.actionPilihJenisSampahFragmentToPickUpFragment(totalharga!!,strTotalBerat,"Kertas Lainnya","kertas")
                        findNavController().navigate(action)
                    }
                }

            }

        }


        hvs.setOnClickListener{
            if (hvs.isChecked){
                karton.isChecked = false
                buku.isChecked = false
                koran.isChecked = false
                bungkusRokok.isChecked = false
                kertasLainnya.isChecked = false
                checkTotal()

            }else if(!hvs.isChecked){
                binding.jsEstimasiharga.setText("Rp. 0")
            }
        }

        karton.setOnClickListener{
            if (karton.isChecked){
                hvs.isChecked = false
                buku.isChecked = false
                koran.isChecked = false
                bungkusRokok.isChecked = false
                kertasLainnya.isChecked = false
                checkTotal()

            }else if(!karton.isChecked){
                binding.jsEstimasiharga.setText("Rp. 0")
            }
        }

        buku.setOnClickListener{
            if (buku.isChecked){
                karton.isChecked = false
                hvs.isChecked = false
                koran.isChecked = false
                bungkusRokok.isChecked = false
                kertasLainnya.isChecked = false
                checkTotal()

            }else if(!buku.isChecked){
                binding.jsEstimasiharga.setText("Rp. 0")
            }
        }

        koran.setOnClickListener{
            if (koran.isChecked){
                karton.isChecked = false
                buku.isChecked = false
                hvs.isChecked = false
                bungkusRokok.isChecked = false
                kertasLainnya.isChecked = false
                checkTotal()

            }else if(!koran.isChecked){
                binding.jsEstimasiharga.setText("Rp. 0")
            }
        }

        bungkusRokok.setOnClickListener{
            if (bungkusRokok.isChecked){
                karton.isChecked = false
                buku.isChecked = false
                koran.isChecked = false
                hvs.isChecked = false
                kertasLainnya.isChecked = false
                checkTotal()

            }else if(!bungkusRokok.isChecked){
                binding.jsEstimasiharga.setText("Rp. 0")
            }
        }

        kertasLainnya.setOnClickListener{
            if (kertasLainnya.isChecked){
                karton.isChecked = false
                buku.isChecked = false
                koran.isChecked = false
                bungkusRokok.isChecked = false
                hvs.isChecked = false
                checkTotal()

            }else if(!kertasLainnya.isChecked){
                binding.jsEstimasiharga.setText("Rp. 0")
            }
        }

        return binding.root
    }

    private fun checkTotal(){

        //berat kertas
        var totalBerat = 0
        var beratKertas = binding.jsItemWeight.text.toString().toInt()

        //jenis kertas
        val hvs = binding.jsHvsCheckbox
        val karton = binding.jsKartonCheckbox
        val koran = binding.jsKoranCheckbox
        val buku = binding.jsBukuCheckbox
        val bungkusRokok = binding.jsBungkusrokokCheckbox
        val kertasLainnya = binding.jsKertaslainnyaCheckbox

        if (hvs.isChecked){
            binding.jsEstimasiharga.setText("Rp. ${beratKertas*800} sd Rp. ${beratKertas*2000}")
            totalharga = (beratKertas*1000).toString()
        }
        if(karton.isChecked){
            binding.jsEstimasiharga.setText("Rp. ${beratKertas*500} sd Rp. ${beratKertas*1200}")
            totalharga = (beratKertas*800).toString()
        }
        if(koran.isChecked){
            binding.jsEstimasiharga.setText("Rp. ${beratKertas*500} sd Rp. ${beratKertas*1000}")
            totalharga = (beratKertas*800).toString()
        }
        if(buku.isChecked){
            binding.jsEstimasiharga.setText("Rp. ${beratKertas*500} sd Rp. ${beratKertas*1000}")
            totalharga = (beratKertas*800).toString()
        }
        if(bungkusRokok.isChecked){
            binding.jsEstimasiharga.setText("Rp. ${beratKertas*5000} sd Rp. ${beratKertas*8000}")
            totalharga = (beratKertas*6500).toString()
        }
        if(kertasLainnya.isChecked){
            binding.jsEstimasiharga.setText("Rp. ${beratKertas*500} sd Rp. ${beratKertas*1000}")
            totalharga = (beratKertas*800).toString()
        }
    }

}