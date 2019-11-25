package br.com.mirabilis.expandable

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.mirabilis.expandable.model.mTop

@Suppress("DEPRECATION")
class vPagerAdapter(fm: FragmentManager, datanya : ArrayList<mTop>) : FragmentPagerAdapter(fm) {

    val byGroup = datanya.groupBy { it.sub.firstOrNull()?.group }
    val dataToList = byGroup.toList()

    override fun getItem(position: Int): Fragment {
        //return BlankFragment.newInstance()
        return vFragment.newInstance(dataToList[position].second as ArrayList<mTop>)
    }

    override fun getPageTitle(position: Int): CharSequence{
        Log.d("vpager2: ",dataToList[position].second.toString())

        return dataToList[position].first.toString()
    }

    override fun getCount(): Int {
        return byGroup.count()
    }

}