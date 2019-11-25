package br.com.mirabilis.expandable.model

data class mTop(val kategori : String, val sub : ArrayList<mSub>)

data class mSub(val id_menu : String, val nama_menu : String, val gambar : String, val group : String)
