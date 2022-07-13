package com.example.testapptyllo

import com.example.testapptyllo.SplashScreen.Companion.cryptoDetails
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ApiCall {

    fun fetchCryptoList() {
        val url =
            "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?CMC_PRO_API_KEY=3d5fdb67-1672-41b4-854c-a1f4c1a8afd5&sort=market_cap&start=1&limit=100&cryptocurrency_type=tokens&convert=USD"
        val request = Request.Builder().url(url).build()

        println(request)
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("EVO IH: $body")
                cryptoDetails.addAll(readJson(body!!))
            }
        })
    }

    fun readJson(fileName: String): MutableList<CryptoDetails> {
        val cryptoDetails: MutableList<CryptoDetails> = ArrayList()
        val jsonObject1 = JSONObject(fileName)
        val dataArray = jsonObject1.getJSONArray("data")
        for (i in 0 until dataArray.length()) {
            val item = dataArray.getJSONObject(i)
            val cryptoName = item.getString("name")
            val lastUpdated = item.getString("last_updated")
            val properties = item.getJSONObject("quote")
            val USD = properties.getJSONObject("USD")
            val price = USD.getString("price")
            val cryptoDetail = CryptoDetails(
                cryptoName,
                lastUpdated,
                price
            )
            cryptoDetails.add(cryptoDetail)
        }
        return cryptoDetails
    }
}