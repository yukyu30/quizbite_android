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
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        textView = findViewById(R.id.textView)

        textView.visibility = View.VISIBLE
        webView.visibility = View.GONE
        textView.text = "Hello!"

    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        textView.visibility = View.GONE
        webView.visibility = View.VISIBLE

        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()

        // 他のWebViewの設定
        webView.settings.javaScriptEnabled = true
        if(intent?.action == Intent.ACTION_SEND && "text/plain" == intent.type) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
                webView.loadUrl("https://quizbite.yukyu.net?url=$it")
            }

        }

    }
}