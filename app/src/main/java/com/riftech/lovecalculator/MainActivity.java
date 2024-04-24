package com.riftech.lovecalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.FormError;
import com.google.android.ump.UserMessagingPlatform;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd,mInterstitialAd2,mInterstitialAd3;
    private EditText et1,et2;
    private Integer code=1;
    private String jsonString="";
    private Integer percentage=null;
    private String percentage2="";
    private ProgressBar pgsBar;
    String status,s = null;
    private View v;
    public Intent intent,intent2,intent3;
    private static final String TAG = "MainActivity";
    private ConsentInformation consentInformation;
    private ConsentForm consentForm ;
    AlertDialog.Builder builder;
    AlertDialog customAlertDialog;
    SharedPreferences sharedPreferences;
    String selected_lang,st,tost;

    String[] countries;
    int selected_index;
    Button btn;
    TextView txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // Set tag for under age of consent. false means users are not under
        // age.
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setTagForUnderAgeOfConsent(false)
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(this);
        consentInformation.requestConsentInfoUpdate(
                this,
                params,
                new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                    @Override
                    public void onConsentInfoUpdateSuccess() {
                        // The consent information state was updated.
                        // You are now ready to check if a form is available.

                        if (consentInformation.isConsentFormAvailable()) {

                            loadForm();
                        }
                    }
                },
                new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                    @Override
                    public void onConsentInfoUpdateFailure(FormError formError) {
                        // Handle the error.
                    }
                });


        // Storing data into SharedPreferences
        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        if(!sharedPreferences.contains("index")) {
// Creating an Editor object to edit(write to the file)
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
            myEdit.putInt("index",0);

// Once the changes have been made, we need to commit to apply those changes made,
// otherwise, it will throw an error
            myEdit.apply();
        }

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
        loadAd3();

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        et1=(EditText) findViewById(R.id.editText2);
        et2=(EditText) findViewById(R.id.editText3);
        btn=(Button) findViewById(R.id.button);
        txt1=(TextView) findViewById(R.id.textView8);
        txt2=(TextView) findViewById(R.id.textView4);

        changelang();
    }




    public void show_how(final View view) {

      intent2 = new Intent(getBaseContext(), MainActivity2.class);
      showInterstitial2();

    }
    public void show_apps(final View view) {

        intent3 = new Intent(getBaseContext(), MainActivity3.class);
        showInterstitial3();

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

    private void gotoActivity3(Intent intent3) {
        startActivity(intent3);
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

    //ca-app-pub-7831928589958637/3603645924
    public void loadAd3() {
        AdRequest adRequest4 = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-7831928589958637/3603645924", adRequest4,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd3 = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                        //Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd3 = null;
                                        Log.d("TAG", "The ad was dismissed.");

                                        loadAd2();
                                        gotoActivity3(intent3);
                                        //dismissed();

                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.mInterstitialAd3 = null;
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
                        mInterstitialAd3 = null;
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
    private void showInterstitial3() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd3 != null) {
            mInterstitialAd3.show(this);


        }

        else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            loadAd3();
            gotoActivity3(intent3);
            //startGame();
        }
    }

    public void loadForm() {
        // Loads a consent form. Must be called on the main thread.
        UserMessagingPlatform.loadConsentForm(
                this,
                new UserMessagingPlatform.OnConsentFormLoadSuccessListener() {
                    @Override
                    public void onConsentFormLoadSuccess(ConsentForm consentForm) {
                        MainActivity.this.consentForm = consentForm;
                        if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                            consentForm.show(
                                    MainActivity.this,
                                    new ConsentForm.OnConsentFormDismissedListener() {
                                        @Override
                                        public void onConsentFormDismissed(@Nullable FormError formError) {
                                            if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.OBTAINED) {
                                                // App can start requesting ads.
                                            }

                                            // Handle dismissal by reloading form.
                                            loadForm();
                                        }
                                    });
                        }
                    }
                },
                new UserMessagingPlatform.OnConsentFormLoadFailureListener() {
                    @Override
                    public void onConsentFormLoadFailure(FormError formError) {
                        // Handle the error.
                    }
                }
        );
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.change, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_favorite) {
            // do something here
            // single item array instance to store which element is selected by user initially
            // it should be set to zero meaning none of the element is selected by default
            selected_index = sharedPreferences.getInt("index", 0);
            final int[] checkedItem = {selected_index};

            // AlertDialog builder instance to build the alert dialog
            builder = new AlertDialog.Builder(MainActivity.this);

            // set the custom icon to the alert dialog
            builder.setIcon(R.drawable.change);

            // title of the alert dialog
            builder.setTitle("Change Language:");

            // list of the items to be displayed to the user in the
            // form of list so that user can select the item from
            final String[] listItems = new String[]{"English","Indonesian", "Español", "Français", "Italiano","Deutsch","Português","Русский"};


            // the function setSingleChoiceItems is the function which
            // builds the alert dialog with the single item selection
            builder.setSingleChoiceItems(listItems, checkedItem[0], (dialog, which) -> {
                // update the selected item which is selected by the user so that it should be selected
                // when user opens the dialog next time and pass the instance to setSingleChoiceItems method
                checkedItem[0] = which;

                // now also update the TextView which previews the selected item
                //tvSelectedItemPreview.setText("Selected Item is : " + listItems[which]);




                SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
                myEdit.putInt("index",which);

// Once the changes have been made, we need to commit to apply those changes made,
// otherwise, it will throw an error
                myEdit.apply();

                changelang();
                // when selected an item the dialog should be closed with the dismiss method
                dialog.dismiss();


            });

            // set the negative button if the user is not interested to select or change already selected item
            builder.setNegativeButton("Cancel", (dialog, which) -> {

            });

            // create and build the AlertDialog instance with the AlertDialog builder instance
            customAlertDialog = builder.create();

            // show the alert dialog when the button is clicked
            customAlertDialog.show();
        }
        if (id == R.id.action_share) {
            // do something here
            // single item array instance to store which element is selected by user initially
            // it should be set to zero meaning none of the element is selected by default
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Love Calculator");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName() +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void changelang() {
        selected_index = sharedPreferences.getInt("index", 0);
        switch (selected_index){
            case 0:

                btn.setText(getText(R.string.btn1));
                et1.setHint(getString(R.string.h1));
                et2.setHint(getString(R.string.h2));
                txt1.setText(getString(R.string.link));
                txt2.setText(getString(R.string.link2));
                tost=getString(R.string.toast);
                this.setTitle(getString(R.string.app_name));
                break;
            case 1:
                btn.setText(getText(R.string.btn1_ind));
                et1.setHint(getString(R.string.h1_ind));
                et2.setHint(getString(R.string.h2_ind));
                txt1.setText(getString(R.string.link_ind));
                txt2.setText(getString(R.string.link2_ind));
                tost=getString(R.string.toast_ind);
                this.setTitle(getString(R.string.app_name_ind));
                break;
            case 2:
                btn.setText(getText(R.string.btn1_es));
                et1.setHint(getString(R.string.h1_es));
                et2.setHint(getString(R.string.h2_es));
                txt1.setText(getString(R.string.link_es));
                txt2.setText(getString(R.string.link2_es));
                tost=getString(R.string.toast_es);
                this.setTitle(getString(R.string.app_name_es));
                break;
            case 3:
                btn.setText(getText(R.string.btn1_fr));
                et1.setHint(getString(R.string.h1_fr));
                et2.setHint(getString(R.string.h2_fr));
                txt1.setText(getString(R.string.link_fr));
                txt2.setText(getString(R.string.link2_fr));
                tost=getString(R.string.toast_fr);
                this.setTitle(getString(R.string.app_name_fr));
                break;
            case 4:
                btn.setText(getText(R.string.btn1_it));
                et1.setHint(getString(R.string.h1_it));
                et2.setHint(getString(R.string.h2_it));
                txt1.setText(getString(R.string.link_it));
                txt2.setText(getString(R.string.link2_it));
                tost=getString(R.string.toast_it);
                this.setTitle(getString(R.string.app_name_it));
                break;
            case 5:
                btn.setText(getText(R.string.btn1_de));
                et1.setHint(getString(R.string.h1_de));
                et2.setHint(getString(R.string.h2_de));
                txt1.setText(getString(R.string.link_de));
                txt2.setText(getString(R.string.link2_de));
                tost=getString(R.string.toast_de);
                this.setTitle(getString(R.string.app_name_de));
                break;
            case 6:
                btn.setText(getText(R.string.btn1_pt));
                et1.setHint(getString(R.string.h1_pt));
                et2.setHint(getString(R.string.h2_pt));
                txt1.setText(getString(R.string.link_pt));
                txt2.setText(getString(R.string.link2_pt));
                tost=getString(R.string.toast_pt);
                this.setTitle(getString(R.string.app_name_pt));
                break;
            case 7:
                btn.setText(getText(R.string.btn1_ru));
                et1.setHint(getString(R.string.h1_ru));
                et2.setHint(getString(R.string.h2_ru));
                txt1.setText(getString(R.string.link_ru));
                txt2.setText(getString(R.string.link2_ru));
                tost=getString(R.string.toast_ru);
                this.setTitle(getString(R.string.app_name_ru));
                break;
            default:
                btn.setText(getText(R.string.btn1));
                et1.setHint(getString(R.string.h1));
                et2.setHint(getString(R.string.h2));
                txt1.setText(getString(R.string.link));
                txt2.setText(getString(R.string.link2));
                tost=getString(R.string.toast);
                this.setTitle(getString(R.string.app_name));
                break;
        }

    }

}
