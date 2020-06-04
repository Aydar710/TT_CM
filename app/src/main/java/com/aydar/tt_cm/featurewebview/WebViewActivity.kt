package com.aydar.tt_cm.featurewebview

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aydar.tt_cm.R
import com.aydar.tt_cm.data.model.User
import com.aydar.tt_cm.featurepersons.presentation.PersonsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_web_view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.MalformedURLException
import java.net.URL


class WebViewActivity : AppCompatActivity() {

    private val viewModel: WebViewViewModel by viewModel()
    private var firebaseUser: FirebaseUser? = null

    private val urls = listOf(
        "http://178.128.242.32/test",
        "http://178.128.242.32/test2"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        firebaseUser = FirebaseAuth.getInstance().currentUser

        if (savedInstanceState != null) {
            web_view.restoreState(savedInstanceState)
        } else {
            configureWebView()
            web_view.loadUrl(urls.random())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        web_view.saveState(outState)
    }

    private fun configureWebView() {
        web_view.settings.javaScriptEnabled = true

        val javaScriptInterface = JavaScriptInterface()
        web_view.addJavascriptInterface(javaScriptInterface, "HTMLOUT")

        val webViewClient: WebViewClient = object : WebViewClient() {

            //Использовать оба метода shouldOverrideUrlLoading(...), для обеспечения
            // работоспособности на старых устройствах
            @SuppressWarnings("deprecation")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(
                view: WebView,
                request: WebResourceRequest
            ): Boolean {
                view.loadUrl(request.url.toString())
                return true
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (urls.contains(url)) {
                    view?.loadUrl("javascript:window.HTMLOUT.processHtml('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');")
                }
            }

        }
        web_view.webViewClient = webViewClient
    }

    private fun saveUserLink(url: String) {
        val user = firebaseUser?.uid?.let { User(it, url) }
        user?.let { viewModel.saveUser(it) }
    }

    private fun getUserLink(userHasLink: (String?) -> Unit) {
        firebaseUser?.uid?.let { viewModel.getUser(it) }
        viewModel.userLiveData.observe(this, Observer {
            if (it?.link != null){
                userHasLink.invoke(it.link)
            }else{
                userHasLink.invoke(null)
            }
        })
    }

    private fun showHasLinkToast() =
        Toast.makeText(this, getString(R.string.has_link_already), Toast.LENGTH_SHORT).show()

    private fun startCardsActivity() {
        startActivity(Intent(this, PersonsActivity::class.java))
    }

    inner class JavaScriptInterface {

        @JavascriptInterface
        fun processHtml(html: String) {
            val htmlText = Html.fromHtml(html).toString()
            if (htmlText.isNotEmpty()) {
                //Проверка, что контент html является URL'ом
                val url = try {
                    URL(htmlText)
                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                    Log.e("T", "Invalid html text")
                    null
                }

                url?.let {
                    web_view.post {
                        try {
                            getUserLink {
                                if (!it.isNullOrEmpty()) {
                                    web_view.loadUrl(it)
                                    showHasLinkToast()
                                } else {
                                    web_view.loadUrl(url.toString())
                                    saveUserLink(url.toString())
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            } else {
                startCardsActivity()
            }
        }
    }
}

