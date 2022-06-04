package com.riftech.lovecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextView txt1 = (TextView) findViewById(R.id.textView2);
        TextView txt2 = (TextView) findViewById(R.id.textView3);
        TextView txt3 = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        String percentage2 = bundle.getString("percentage2","h");
        Integer percentage = Integer.parseInt(percentage2);
        String name = bundle.getString("names","Romeo & Juliet");
        String result = null;

if(percentage<=30){result="Not bad";}
else if( percentage<=50){result="Good";}
else if( percentage<=70){result="Excellent";}
else if(percentage<=100){result="Is there any doubt? You are rocking.";}


        txt1.setText(String.valueOf(percentage2)+"%");
        txt2.setText(result);
        txt3.setText("Result");

    }


}
