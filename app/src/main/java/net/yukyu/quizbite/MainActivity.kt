package net.yukyu.quizbite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.webkit.WebView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //webviewの初期設定
        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

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