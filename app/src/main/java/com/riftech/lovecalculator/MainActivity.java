package com.riftech.lovecalculator;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd,mInterstitialAd2;
    private EditText et1,et2;
    private Integer code=1;
    private String jsonString="";
    private Integer percentage=null;
    private String percentage2="";
    private ProgressBar pgsBar;
    String status,s = null;
    private View v;
    public Intent intent,intent2;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       loadAd();
        loadAd2();

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        et1=(EditText) findViewById(R.id.editText2);
        et2=(EditText) findViewById(R.id.editText3);
    }




    public void show_how(final View view) {

      intent2 = new Intent(getBaseContext(), MainActivity2.class);
      showInterstitial2();

    }
    public void start_match( final View view) {
        String name1=et1.getText().toString();
        String name2=et2.getText().toString();
        if (name1.matches("")||name2.matches("")) {
            Toast.makeText(this, "You did not enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
        pgsBar.setVisibility(v.VISIBLE);


        percentage2=calculate(name1,name2);

        pgsBar.setVisibility(v.GONE);
        intent = new Intent(getBaseContext(), Main2Activity.class);
        intent.putExtra("percentage", percentage);
        intent.putExtra("percentage2", percentage2);
        intent.putExtra("names", name1+" & "+name2);
        showInterstitial();



    }

    private String calculate(String name1, String name2) {
        String s=name1+"loves"+name2;
        String word=s.toLowerCase();
        String number= "";
        int wordCount = 0;

        for (int j = 0;  word.length()>0; j++) {
            char letter=word.charAt(0);
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    wordCount++;
                }
            }
            word=word.replace(Character.toString(letter),"");
            if (wordCount > 0) {
                number=number+Integer.toString(wordCount);
                //System.out.println(letter + "=" + wordCount);
                wordCount = 0;
            }
        }
        String number2=number;
        for(int i=0;number2.length()>2;i++){
            number=number2;
number2="";

            for(int j=0 ;j<(number.length()/2);j++) {
                int a = Character.getNumericValue(number.charAt(j));
                int b = Character.getNumericValue(number.charAt(number.length()-j- 1));
                int c= a+b;
                number2 = number2 + String.valueOf(c);
                if(j+1==number.length()/2 && number.length()%2==1){
                    number2 = number2 + number.charAt(j+1);
                }

            }
            //number = number.replace(String.valueOf(number.charAt(0)), "");
            //number = number.replace(String.valueOf(number.charAt(number.length() - 1)), "");
        }

        return number2;
    }

    private void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    private void gotoActivity2(Intent intent2) {
        startActivity(intent2);
    }


    public void loadAd() {
        AdRequest adRequest2 = new AdRequest.Builder().build();
//ca-app-pub-3940256099942544/1033173712
//ca-app-pub-7831928589958637/6704201737
        InterstitialAd.load(this,"ca-app-pub-7831928589958637/6704201737", adRequest2,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd = null;
                                        Log.d("TAG", "The ad was dismissed.");

                                        gotoActivity(intent);
                                        //dismissed();

                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                       /* String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        Toast.makeText(
                                MainActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                .show();*/
                    }

                });
    }
    public void loadAd2() {
        AdRequest adRequest3 = new AdRequest.Builder().build();
//ca-app-pub-3940256099942544/1033173712
//ca-app-pub-7831928589958637/9538086933
        InterstitialAd.load(this,"ca-app-pub-7831928589958637/9538086933", adRequest3,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd2 = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd2 = null;
                                        Log.d("TAG", "The ad was dismissed.");

                                        loadAd2();
                                        gotoActivity2(intent2);
                                        //dismissed();

                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd2 = null;
                                        Log.d("TAG", "The ad failed to show.");
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        Log.d("TAG", "The ad was shown.");
                                    }
                                });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd2 = null;
                       /* String error =
                                String.format(
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(), loadAdError.getCode(), loadAdError.getMessage());
                        Toast.makeText(
                                MainActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                .show();*/
                    }

                });
    }
    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);


            }

        else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
                loadAd();
            gotoActivity(intent);
            //startGame();
        }
    }

    private void showInterstitial2() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd2 != null) {
            mInterstitialAd2.show(this);


        }

        else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            loadAd2();
            gotoActivity2(intent2);
            //startGame();
        }
    }

}
