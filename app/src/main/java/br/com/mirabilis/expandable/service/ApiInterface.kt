package br.com.mirabilis.expandable.service
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Query

interface ApiInterface {

    @HTTP(method="GET", path = "api/menu/list",hasBody = false)
    fun getTechnoList(): Call<String>

}
