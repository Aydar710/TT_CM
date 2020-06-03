package com.aydar.tt_cm.featuresplash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aydar.tt_cm.R
import com.aydar.tt_cm.featuremain.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startMainActivity()
        /*Handler().postDelayed({
            startMainActivity()
        }, 5000)*/
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
