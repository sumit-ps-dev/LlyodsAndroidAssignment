package com.llyods.assignment.presentation.views.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.llyods.assignment.databinding.FragmentArtistDetailBinding
import com.llyods.assignment.utility.Constant


class ArtistDetailFragment : Fragment() {
    private val TAG = ArtistDetailFragment::class.java.canonicalName

    private lateinit var binding: FragmentArtistDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            webview.settings.javaScriptEnabled = true
            webview.webViewClient = MyWebViewClient()

            arguments?.getString(Constant.ARTIST_URL)?.let {
                webview.loadUrl(it)
            }?: run {
                Log.e(TAG,"Failed to load Webview")
            }
        }

    }

    class MyWebViewClient: WebViewClient() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }
        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }
        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
        }
    }

}