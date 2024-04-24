package com.riftech.lovecalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    Intent intent,intent2;
    ImageButton imgbutton1,imgbutton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
        imgbutton1= (ImageButton) findViewById(R.id.imageButton);
        imgbutton2= (ImageButton) findViewById(R.id.imageButton2);



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