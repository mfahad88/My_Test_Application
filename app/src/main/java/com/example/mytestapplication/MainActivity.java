package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        gridLayout=findViewById(R.id.gridLayout);
    }

    public void openWebview(View view) {
        String stringBtn=((TextView)view).getText().toString();
        if (getResources().getString(R.string.button_1).equalsIgnoreCase(stringBtn)) {

        }else if (getResources().getString(R.string.button_2).equalsIgnoreCase(stringBtn)){

        }else if (getResources().getString(R.string.button_3).equalsIgnoreCase(stringBtn)){

        }else if (getResources().getString(R.string.button_4).equalsIgnoreCase(stringBtn)){

        }else if (getResources().getString(R.string.button_5).equalsIgnoreCase(stringBtn)){

        }else if (getResources().getString(R.string.button_6).equalsIgnoreCase(stringBtn)){

        }else if (getResources().getString(R.string.button_7).equalsIgnoreCase(stringBtn)){

        }else if (getResources().getString(R.string.button_8).equalsIgnoreCase(stringBtn)){

        }

    }
}
