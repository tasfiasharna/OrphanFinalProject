//package com.tasfia.orphanhouse;
//
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.RelativeLayout;
//
//public class DonateActivity extends AppCompatActivity {
//
//    private RelativeLayout container;
//    private WebView mWebView;
//    ProgressDialog prDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_donate);
//
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Back");
//
//        initId();
//
//        webViewSetup();
//    }
//
//    private void initId(){
//        mWebView = findViewById(R.id.webView_Id);
//        container = findViewById(R.id.rootLayout);
//    }
//
//    private void webViewSetup() {
//
//        mWebView.setBackgroundColor(Color.TRANSPARENT);
//
//        WebSettings mWebSettings = mWebView.getSettings();
//
//        mWebView.getSettings().setDatabaseEnabled(true);
//
//        mWebSettings.setJavaScriptEnabled(true);                             //Enable javascript execution.
//
//        mWebSettings.setDomStorageEnabled(true);                             //Set whether the DOM storage API is enabled.
//
//        mWebSettings.setDatabaseEnabled(true);
//
//        mWebSettings.setBuiltInZoomControls(false);                          //Remove +/- controls on screen
//
//        mWebSettings.setAllowFileAccess(true);
//        mWebSettings.setAllowContentAccess(true);
//
//        mWebSettings.setLoadWithOverviewMode(true);
//        mWebSettings.setUseWideViewPort(true);
//
//        mWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);     //speed up page load
//
//        mWebSettings.setPluginState(WebSettings.PluginState.ON);             //Any object will be loaded even if a plugin does not exist to handle the content.
//
//        mWebSettings.setAppCacheEnabled(true);
//        mWebSettings.setAppCacheMaxSize(1024 * 8);
//        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);                //Don't use the cache, load from the network and speed up page load
//
//        mWebSettings.setDefaultTextEncodingName("utf-8");
//
//        mWebView.requestFocus(View.FOCUS_DOWN);                             //speed up page load
//
//        mWebView.getSettings().setSupportZoom(true);
//        mWebView.getSettings().setBuiltInZoomControls(true);
//        mWebView.getSettings().setDisplayZoomControls(false);
//
//        mWebView.setWebViewClient(new WebViewClient());
//
//        mWebView.setWebChromeClient(new WebChromeClient());
//
//        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//
//        mWebView.loadUrl("http://192.168.0.105:1215/ViewVolunteersGivenWork/GetVolunteers");
//
//        prDialog = new ProgressDialog(DonateActivity.this);
//        prDialog.setMessage("Please wait ...");
//        prDialog.show();
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(mWebView.canGoBack()){
//            mWebView.goBack();
//        }
//        else {
//            super.onBackPressed();
//        }
//    }
//}
