package br.com.mirabilis.expandable.service
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BasicAuthClient<T> {
    private val client =  OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", "PwpPJFTqrPKD2IEUVSP0vEr37m5VPTPkCMbTL2gu"))
        .build()

    val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://maxxapi.technopartner.rocks/")
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    fun create(service: Class<T>): T {
        return retrofit.create(service)
    }
}
