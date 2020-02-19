package com.example.mytestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WebClientActivity extends AppCompatActivity {
    ProgressBar progressBar;
    WebView webView;
    String url;
    LinearLayout linearHeader;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_client);
        initViews();
        if(getIntent()!=null){
            url=getIntent().getStringExtra(MainActivity.URL);
        }

        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                getSupportActionBar().setTitle(title);
                linearHeader.setVisibility(View.GONE);
                RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.setMargins(0,0,0,0);
                webView.setLayoutParams(params);
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                WebClientActivity.this.title=title;
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);


                getSupportActionBar().setDisplayShowHomeEnabled(true);
                Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(icon, 80, 80, true));
                getSupportActionBar().setLogo(d);
                getSupportActionBar().setDisplayUseLogoEnabled(true);

            }
        });

    }

    private void initViews() {
        progressBar=findViewById(R.id.progressBar);
        webView=findViewById(R.id.webView);
        linearHeader=findViewById(R.id.linearHeader);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_refresh){
            Toast.makeText(this, "Reloading...", Toast.LENGTH_SHORT).show();
            webView.loadUrl(url);
        }
        return super.onOptionsItemSelected(item);
    }
}
