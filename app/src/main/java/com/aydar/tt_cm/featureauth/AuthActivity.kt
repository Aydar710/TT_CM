package com.aydar.tt_cm.featureauth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aydar.tt_cm.R
import com.aydar.tt_cm.featuresplash.SplashActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    private val authHelper = AuthHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (authHelper.checkIfUserSignedIn()) {
            startSplashActivity()
        }

        authHelper.onCodeSent = {
            txt_input_received_code.visibility = View.VISIBLE
            btn_sign_in.visibility = View.VISIBLE
            Toast.makeText(this, "Код отправлен", Toast.LENGTH_SHORT).show()
        }

        authHelper.onVerificationFailed = {
            Toast.makeText(this, "Не удалось авторизоваться", Toast.LENGTH_SHORT).show()
            txt_input_received_code.visibility = View.GONE
            btn_sign_in.visibility = View.GONE
        }

        authHelper.onVerificationCompleted = {
            et_received_code.setText(it)
            btn_sign_in.isEnabled = false
        }

        authHelper.onSignedIn = {
            startSplashActivity()
        }


        btn_send.setOnClickListener {
            authHelper.verifyPhoneNumber(et_phone.text.toString())
        }

        btn_sign_in.setOnClickListener {
            authHelper.signInWithCode(et_received_code.text.toString())
        }

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                val strBefore: String = charSequence.toString()
                if (strBefore.length == 1) {
                    if (strBefore == "8") {
                        val strAfter: String = charSequence.toString().replace("8", "+7")
                        et_phone.setText(strAfter)
                        et_phone.setSelection(charSequence!!.length + 1)
                    }
                    if (strBefore == "7") {
                        val strAfter: String = charSequence.toString().replace("7", "+7")
                        et_phone.setText(strAfter)
                        et_phone.setSelection(charSequence!!.length + 1)
                    }
                    if (strBefore == "9") {
                        val strAfter: String = charSequence.toString().replace("9", "+79")
                        et_phone.setText(strAfter)
                        et_phone.setSelection(charSequence!!.length + 2)
                    }
                }
            }

        })
    }

    private fun startSplashActivity() {
        startActivity(Intent(this, SplashActivity::class.java))
    }
}
