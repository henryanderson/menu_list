package br.com.mirabilis.expandable

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import br.com.mirabilis.expandable.model.mSub
import br.com.mirabilis.expandable.model.mTop
import br.com.mirabilis.expandable.service.ApiInterface
import br.com.mirabilis.expandable.service.BasicAuthClient
import com.google.android.material.tabs.TabLayout
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class main : AppCompatActivity() {

    val mtop: ArrayList<mTop> = ArrayList()

    var tabLayout : TabLayout?=null
    var viewPager : ViewPager?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        initToolbar()

        tabLayout = findViewById(R.id.tab_layout)
        viewPager = findViewById(R.id.view_pager)

        ambilJSON()

    }

    fun ambilJSON() {

        val call = BasicAuthClient<ApiInterface>().create(ApiInterface::class.java)
            .getTechnoList()

        call.enqueue(object : Callback<String> {

            @SuppressLint("WrongConstant")
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if (response?.isSuccessful!!) {
                    if (response.body() != null) {

                        val json = response.body()
                        val jsonObject = JSONObject(json.toString())

                        val menu = covertJsonObjectToJsonArray(jsonObject.get("menu"))

                        for (i in 0 until menu.length()) {
                            val obj = menu.getJSONObject(i)
                            val keys = obj.keys()

                            while (keys.hasNext()) {
                                val currentDynamicKey = keys.next() as String
                                //Log.d("hasil ", currentDynamicKey)

                                val currentDynamicValue = obj.getJSONArray(currentDynamicKey)

                                val msub: ArrayList<mSub> = ArrayList()

                                for (i in 0 until currentDynamicValue.length()) {

                                    val nameObj = currentDynamicValue.getJSONObject(i)
                                    val nama_menu = nameObj.getString("nama_menu")
                                    //Log.d("hasil nama_menu", nama_menu)
                                    val gambar = nameObj.getString("gambar")
                                    //Log.d("hasil gambar", gambar)
                                    val id_Menu = nameObj.getString("id_Menu")
                                    val group = nameObj.getString("group")

                                    msub.add(mSub(id_Menu,nama_menu,gambar,group));

                                }
                                val header = currentDynamicKey
                                var msubRe: ArrayList<mSub>
                                msubRe = msub

                                //Log.d("hasils : ", msubRe.toString())

                                mtop.add(mTop(header, msubRe))

                            }
                        }
                        Log.d("hasil1 : ", mtop.toString())

                        //val henry = mtop.filter { it.sub.isNotEmpty() }.groupBy { it.sub[0].group }
                        //Log.d("hasilhenry : ", henry.toString())

                        val adapter = vPagerAdapter(supportFragmentManager, mtop)
                        viewPager?.adapter = adapter
                        tabLayout?.setupWithViewPager(viewPager)

                    }
                }

            }

            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("hasil fail ", "hasil " + t)
            }

        })
    }


    fun covertJsonObjectToJsonArray(InsideArray: Any): JSONArray {

        val jsonArray: JSONArray

        if (InsideArray is JSONArray) {
            jsonArray = InsideArray
        } else {
            jsonArray = JSONArray()
            jsonArray.put(InsideArray as JSONObject)
        }
        return jsonArray
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //supportActionBar!!.title = "DAFTAR MENU"
    }

}