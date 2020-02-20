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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    public static final String URL="";
    InterstitialAd mInterstitialAd;
    ScheduledExecutorService scheduler;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!scheduler.isShutdown()){
            scheduler.shutdown();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        prepareAd();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Toast.makeText(MainActivity.this, ""+initializationStatus.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Log.e(this.getClass().getSimpleName(),"Scheduler started...");
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       if(mInterstitialAd.isLoaded()){
                           mInterstitialAd.show();
                       }else{
                           Log.e(this.getClass().getSimpleName(),"Interstitial not loaded...");
                       }
                       prepareAd();
                   }
               });
            }
        },30,30, TimeUnit.SECONDS);

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

    public void prepareAd(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); //Interstitial ID
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
