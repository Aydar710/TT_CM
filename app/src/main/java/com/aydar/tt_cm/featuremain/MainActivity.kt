package com.aydar.tt_cm.featuremain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aydar.tt_cm.R
import com.aydar.tt_cm.featurewebview.WebViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startWebViewActivity()
        btn_start.setOnClickListener {
            startWebViewActivity()
        }

        tv_privacy_policy_link.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(PRIVACY_POLICY_LINK))
            startActivity(intent)
        }

    }

    private fun startWebViewActivity() {
        startActivity(Intent(this, WebViewActivity::class.java))
    }
}
