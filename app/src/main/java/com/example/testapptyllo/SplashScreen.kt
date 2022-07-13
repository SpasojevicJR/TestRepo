package com.example.testapptyllo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.loading_screen.*

class SplashScreen : AppCompatActivity() {

    companion object {
        var cryptoDetails: MutableList<CryptoDetails> = mutableListOf()
    }

    private val SPLASH_DELAY: Long = 2000 //3 seconds
    private var mDelayHandler: Handler? = null
    private var progressBarStatus = 0
    var dummy: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading_screen)

        ApiCall().fetchCryptoList()

        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)
        runAnims()
    }

    fun runAnims() {
        val animationBounce = AnimationUtils.loadAnimation(this, R.anim.bounce)
        val animationBlink = AnimationUtils.loadAnimation(this, R.anim.blink)

        bnbImage.startAnimation(animationBounce)
        bitcoinImage.startAnimation(animationBounce)
        shibaImage.startAnimation(animationBounce)
        etheriumImage.startAnimation(animationBlink)
    }

    private fun launchMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        this.finish()
        mDelayHandler!!.removeCallbacks(mRunnable)
    }

    private val mRunnable: Runnable = Runnable {

        Thread(Runnable {
            while (progressBarStatus < 100) {
                try {
                    dummy += 25
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                progressBarStatus = dummy
            }
            launchMainActivity()
        }).start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }
    }
}