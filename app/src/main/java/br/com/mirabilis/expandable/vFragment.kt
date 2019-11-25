package br.com.mirabilis.expandable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mirabilis.expandable.expandable.ExpandableHeaderItem
import br.com.mirabilis.expandable.expandable.FancyItem
import br.com.mirabilis.expandable.model.mTop
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class vFragment : Fragment() {

    private lateinit var rv: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rv = view.findViewById(R.id.recycler_view)

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            spanCount = 2
        }

        rv.apply {
            layoutManager = GridLayoutManager(rootView.context, groupAdapter.spanCount).apply {
                spanSizeLookup = groupAdapter.spanSizeLookup
            }
            adapter = groupAdapter
        }

        var headerTab: ArrayList<mTop>
        headerTab = arguments?.getSerializable("headertab") as ArrayList<mTop>


        for (h in 0 until headerTab.size) {
            val header = headerTab.get(h).kategori
            var status = false
            if(h==0){status=true}

            ExpandableGroup(ExpandableHeaderItem(header), status).apply {

                for (c in 0 until headerTab[h].sub.size) {
                    val gambar = (headerTab[h].sub).get(c).gambar
                    val nama_menu = (headerTab[h].sub).get(c).nama_menu
                    add(Section(FancyItem(gambar, nama_menu)))
                }

                groupAdapter.add(this)
            }

        }
    }


    companion object {
        fun newInstance(headertab: ArrayList<mTop>): vFragment {
            val f = vFragment()

            val args = Bundle()
            args.putSerializable("headertab", headertab)

            f.setArguments(args)
            return f
        }
    }


}
