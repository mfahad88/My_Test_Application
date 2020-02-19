package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    public static final String URL="";
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(MainActivity.this, ""+initializationStatus.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(MainActivity.this,
                        "onAdFailedToLoad() with error code: " + errorCode,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {

            }
        });
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }


        /*mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }*/


    }

    private void initViews() {
        gridLayout=findViewById(R.id.gridLayout);
    }

    public void openWebview(View view) {
        String stringBtn=((TextView)view).getText().toString();
        Intent intent=new Intent(this,WebClientActivity.class);
        if (getResources().getString(R.string.button_1).equalsIgnoreCase(stringBtn)) {
            intent.putExtra(URL,"http://www.google.com.pk");
        }else if (getResources().getString(R.string.button_2).equalsIgnoreCase(stringBtn)){
            intent.putExtra(URL,"http://www.outlook.com");
        }else if (getResources().getString(R.string.button_3).equalsIgnoreCase(stringBtn)){
            intent.putExtra(URL,"http://www.yahoo.com");
        }else if (getResources().getString(R.string.button_4).equalsIgnoreCase(stringBtn)){
            intent.putExtra(URL,"https://www.speedtest.net");
        }else if (getResources().getString(R.string.button_5).equalsIgnoreCase(stringBtn)){
            intent.putExtra(URL,"http://www.gmail.com");
        }else if (getResources().getString(R.string.button_6).equalsIgnoreCase(stringBtn)){
            intent.putExtra(URL,"http://www.skype.com");
        }else if (getResources().getString(R.string.button_7).equalsIgnoreCase(stringBtn)){
                intent.putExtra(URL,"https://jang.com.pk");
        }else if (getResources().getString(R.string.button_8).equalsIgnoreCase(stringBtn)){
            intent.putExtra(URL,"https://www.w3schools.com/");
        }
        startActivity(intent);
    }
}
