package net.yukyu.quizbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.webkit.WebView
import android.widget.Toolbar


class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //webViewの初期設定
        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        if (intent?.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                webView.loadUrl("${QUIZBITE_URL}?url=$it")
            }
        } else {
            webView.loadUrl(QUIZBITE_URL)
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if(intent?.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                webView.loadUrl(Companion.QUIZBITE_URL)
            }
        } else {
            webView.loadUrl(QUIZBITE_URL)
        }
    }

    companion object {
        private const val QUIZBITE_URL = "https://quizbite.yukyu.net"
    }
}