package com.ttc.trashtocash.register

data class Order (
    var id_penyetor: String? = null,
    var tgl_jual: String? = null,
    var tgl_jemput: String?= null,
    var waktu_jemput: String? = null,
    var no_telp: String? = null,
    var jenis: String?= null,
    var jumlah: String?= null,
    var harga: String?= null,
    var alamat: String? = null,
    var tambahan: String? = null,
    var img_link: String? = "https://firebasestorage.googleapis.com/v0/b/sisteminformasi-449ae.appspot.com/o/item%2Fkertasdefault.png?alt=media&token=f7ec5844-b848-4b59-9fd4-4d722cc869df"
)