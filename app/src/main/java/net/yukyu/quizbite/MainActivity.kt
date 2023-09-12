package net.yukyu.quizbite

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebResourceRequest
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
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                request?.let {
                    // twitter.comのリンクの場合、外部ブラウザを起動します
                    if (!it.url.toString().contains(QUIZBITE_URL)) {
                        val intent = Intent(Intent.ACTION_VIEW, it.url)
                        startActivity(intent)
                        return true
                    }
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

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
                webView.loadUrl("${QUIZBITE_URL}?url=$it")
            }
        } else {
            webView.loadUrl(QUIZBITE_URL)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 戻るボタンが押される かつ webviewで前に戻ることができるとき
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            // 前のページに戻る
            webView.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }



    companion object {
        private const val QUIZBITE_URL = "https://quizbite.yukyu.net"
    }
}