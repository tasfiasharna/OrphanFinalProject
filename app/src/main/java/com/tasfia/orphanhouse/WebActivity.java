package com.tasfia.orphanhouse;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends AppCompatActivity {

    private WebView                 mWebView;
    private ValueCallback<Uri[]>    uploadMessage;
    private final int               FILE_CHOOSER_RESULT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webs);

        initId();

        webViewSetup();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
    }

    private void initId(){
        mWebView = findViewById(R.id.web);
    }

    private void webViewSetup() {
        mWebView.setBackgroundColor(Color.TRANSPARENT);

        WebSettings mWebSettings = mWebView.getSettings();

        mWebView.getSettings().setDatabaseEnabled(true);

        mWebSettings.setJavaScriptEnabled(true);                             //Enable javascript execution.

        mWebSettings.setDomStorageEnabled(true);                             //Set whether the DOM storage API is enabled.

        mWebSettings.setDatabaseEnabled(true);

        mWebSettings.setBuiltInZoomControls(false);                          //Remove +/- controls on screen

        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setAllowContentAccess(true);

        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);

        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);     //speed up page load

        mWebSettings.setPluginState(WebSettings.PluginState.ON);             //Any object will be loaded even if a plugin does not exist to handle the content.

        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setAppCacheMaxSize(1024 * 8);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);                //Don't use the cache, load from the network and speed up page load

        mWebSettings.setDefaultTextEncodingName("utf-8");

        mWebView.requestFocus(View.FOCUS_DOWN);                             //speed up page load

        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);

        mWebView.setWebViewClient(new WebClientClass());

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, FILE_CHOOSER_RESULT_CODE);
                }
                catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(WebActivity.this, "Sorry, cannot Open File Chooser", Toast.LENGTH_SHORT).show();
                    Log.e("Orphan_House", "Cannot Open File Chooser, reason: "+ e.getMessage());
                    return false;
                }

                return true;
            }


        });

        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mWebView.loadUrl(getIntent().getStringExtra("url"));
    }

    public class WebClientClass extends WebViewClient {
        ProgressDialog pd = new ProgressDialog(WebActivity.this);

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (pd != null && !pd.isShowing()) {
                pd.setMessage("Loading...");
                pd.show();
            }

            if(url.equals("http://localhost:1215/Donator/Success")
                    || url.equals("localhost:1215/Donator/Success")
                    || url.equals("http://localhost:1215/Payment/Success")
                    || url.equals("localhost:1215/Payment/Success")){
                mWebView.loadUrl(url.replace("localhost", "192.168.0.105"));
            }
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == FILE_CHOOSER_RESULT_CODE) {
                if (uploadMessage == null) return;

                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else {
            Toast.makeText(WebActivity.this, "Sorry, failed to Upload Image, please try again", Toast.LENGTH_SHORT).show();
            Log.e("Orphan_House", "Failed to Upload Image");
        }
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()){
            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    // app icon in action bar clicked; goto parent activity.
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
