package br.com.mirabilis.expandable.expandable


import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import br.com.mirabilis.expandable.R

import kotlinx.android.synthetic.main.item_expandable_item.*


class FancyItem(private val gambar: String, private val nama_menu: String) : Item(){


    override fun bind(viewHolder: ViewHolder, position: Int) {

        Glide.with(viewHolder.imgGambar.context).load(gambar).into(viewHolder.imgGambar)
        viewHolder.tvNamaMenu.text = nama_menu.toString()
    }


    override fun getLayout() = R.layout.item_expandable_item

    override fun getSpanSize(spanCount: Int, position: Int) = spanCount / 2
}
