package com.riftech.lovecalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity3 extends AppCompatActivity {

    Intent intent,intent2;
    ImageButton imgbutton1,imgbutton2,imgbutton3,imgbutton4,imgbutton5,imgbutton6,imgbutton7,imgbutton8;
    Button button;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        // Set the status bar text color to dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        mAdView = findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
        imgbutton1= (ImageButton) findViewById(R.id.imageButton);
        imgbutton2= (ImageButton) findViewById(R.id.imageButton2);
        imgbutton3= (ImageButton) findViewById(R.id.imageButton3);
        imgbutton4= (ImageButton) findViewById(R.id.imageButton4);
        imgbutton5= (ImageButton) findViewById(R.id.imageButton5);
        imgbutton6= (ImageButton) findViewById(R.id.imageButton6);
        imgbutton7= (ImageButton) findViewById(R.id.imageButton7);
        imgbutton8= (ImageButton) findViewById(R.id.imageButton8);
        button= (Button) findViewById(R.id.seeMoreAppsButton);



        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imageButton:
                        //Start activity 1 here, for example
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.guessthecountry"));
                        startActivity(intent);
                        break;
                    case R.id.imageButton2:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.flamesgame"));
                        startActivity(intent2);
                        break;
                    case R.id.imageButton3:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.todolist"));
                        startActivity(intent2);
                        break;
                    case R.id.imageButton4:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.bmicalculator"));
                        startActivity(intent2);
                        break;
                    case R.id.imageButton5:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.a99namesofallah"));
                        startActivity(intent2);
                        break;
                    case R.id.imageButton6:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.randomhadith"));
                        startActivity(intent2);
                        break;
                    case R.id.imageButton7:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.lovequotes"));
                        startActivity(intent2);
                        break;
                    case R.id.imageButton8:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.bodyfatcalculatoren"));
                        startActivity(intent2);
                        break;
                    case R.id.seeMoreAppsButton:
                        //Start activity 2 here
                        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Rifat softwares"));
                        startActivity(intent2);
                        break;

                }

            }
        };

        imgbutton1.setOnClickListener(listener);
        imgbutton2.setOnClickListener(listener);
        imgbutton3.setOnClickListener(listener);
        imgbutton4.setOnClickListener(listener);
        imgbutton5.setOnClickListener(listener);
        imgbutton6.setOnClickListener(listener);
        imgbutton7.setOnClickListener(listener);
        imgbutton8.setOnClickListener(listener);
        button.setOnClickListener(listener);


//        });
    }


    public void guess(final View view){
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.guessthecountry"));
        startActivity(intent);
    }
    public void flames(final View view){
        intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.riftech.flamesgame"));
        startActivity(intent2);
    }
}