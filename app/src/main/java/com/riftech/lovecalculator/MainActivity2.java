package com.riftech.lovecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity2 extends AppCompatActivity {

    private AdView mAdView;
    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

         mAdView = findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        txt1=(TextView) findViewById(R.id.textView5);
        txt2=(TextView) findViewById(R.id.textView6);

        changelang();
    }
    public void changelang() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        int selected_index = sharedPreferences.getInt("index", 0);
        switch (selected_index){
            case 0:
                txt1.setText(getString(R.string.how));
                txt2.setText(getString(R.string.fun));
                this.setTitle(getString(R.string.app_name));
                break;
            case 1:
                txt1.setText(getString(R.string.how_ind));
                txt2.setText(getString(R.string.fun_ind));
                this.setTitle(getString(R.string.app_name_ind));
                break;
            case 2:
                txt1.setText(getString(R.string.how_es));
                txt2.setText(getString(R.string.fun_es));
                this.setTitle(getString(R.string.app_name_es));
                break;
            case 3:
                txt1.setText(getString(R.string.how_fr));
                txt2.setText(getString(R.string.fun_fr));
                this.setTitle(getString(R.string.app_name_fr));
                break;
            case 4:
                txt1.setText(getString(R.string.how_it));
                txt2.setText(getString(R.string.fun_it));
                this.setTitle(getString(R.string.app_name_it));
                break;
            case 5:
                txt1.setText(getString(R.string.how_de));
                txt2.setText(getString(R.string.fun_de));
                this.setTitle(getString(R.string.app_name_de));
                break;
            case 6:
                txt1.setText(getString(R.string.how_pt));
                txt2.setText(getString(R.string.fun_pt));
                this.setTitle(getString(R.string.app_name_pt));
                break;
            case 7:
                txt1.setText(getString(R.string.how_ru));
                txt2.setText(getString(R.string.fun_ru));
                this.setTitle(getString(R.string.app_name_ru));
                break;
            default:
                txt1.setText(getString(R.string.how));
                txt2.setText(getString(R.string.fun));
                this.setTitle(getString(R.string.app_name));
                break;
        }

    }

}