package com.example.testapptyllo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapptyllo.SplashScreen.Companion.cryptoDetails
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoDetailsAdapter: CryptoDetailsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        cryptoRecyclerView1.layoutManager = linearLayoutManager
        cryptoDetailsAdapter = CryptoDetailsAdapter(this, cryptoDetails)
        cryptoRecyclerView1.adapter = cryptoDetailsAdapter
        cryptoRecyclerView1.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onStart() {
        super.onStart()
        if (cryptoDetails.size == 0) {
            ApiCall().fetchCryptoList()
        }
    }
}