package com.timerlearning.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.timerlearning.R;
import com.timerlearning.utils.Constants;

import java.util.Objects;

public class WebActivity extends AppCompatActivity {

    private WebView webview;
    private static final String TAG = "Main";

    private TextView loading;
    private ProgressBar progressBar;

    /** Called when the activity is first created. */
    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("WEB PIS");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ab.setLogo(R.drawable.logo);

        setContentView(R.layout.activity_web);

        this.webview = findViewById(R.id.webview);

        // --------------   Show loading
        progressBar = findViewById(R.id.progressBarWeb);
        loading = findViewById(R.id.loadingWeb);

        webview.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        // --------------- End Show Loading

        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());

        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        webview.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // --------------   Show loading
                webview.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                // --------------- End Show Loading
                view.loadUrl(url);
                return true;

            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
                progressBar.setVisibility(View.INVISIBLE);
                loading.setVisibility(View.INVISIBLE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(TAG, "Error: " + description);
                Toast.makeText(WebActivity.this, "Check your connection!", Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });
        webview.setVisibility(View.VISIBLE);
        webview.loadUrl(Constants.URL_SP_DASHBOARD);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
