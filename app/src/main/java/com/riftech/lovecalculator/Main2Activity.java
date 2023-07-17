package com.riftech.lovecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class Main2Activity extends AppCompatActivity {

    TextView txt1,txt2,txt3;
String rs1,rs2,rs3,rs4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        AdView mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

         txt1 = (TextView) findViewById(R.id.textView2);
         txt2 = (TextView) findViewById(R.id.textView3);
         txt3 = (TextView) findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        String percentage2 = bundle.getString("percentage2","h");
        Integer percentage = Integer.parseInt(percentage2);
        String name = bundle.getString("names","Romeo & Juliet");
        String result = null;

        changelang();

if(percentage<=30){result=rs1;}
else if( percentage<=50){result=rs2;}
else if( percentage<=70){result=rs3;}
else if(percentage<=100){result=rs4;}


        txt1.setText(String.valueOf(percentage2)+"%");
        txt2.setText(result);


    }
    public void changelang() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        int selected_index = sharedPreferences.getInt("index", 0);
        switch (selected_index){
            case 0:
                txt3.setText("Result");
                rs1="Not Bad";
                rs2="Good";
                rs3="Excellent";
                rs4="Is there any doubt? You are rocking.";
                this.setTitle(getString(R.string.app_name));
                break;
            case 1:
                txt3.setText("Hasil");
                rs1="Tidak buruk";
                rs2="Bagus";
                rs3="Bagus sekali";
                rs4="Apakah ada keraguan? Anda goyang.";
                this.setTitle(getString(R.string.app_name_ind));
                break;
            case 2:
                txt3.setText("Resultado");
                rs1="No malo";
                rs2="Bueno";
                rs3="Excelente";
                rs4="¿Hay alguna duda? Estás rockeando.";
                this.setTitle(getString(R.string.app_name_es));
                break;
            case 3:
                txt3.setText("Résultat");
                rs1="Pas mal";
                rs2="Bien";
                rs3="Excellent";
                rs4="Y a-t-il un doute? Vous basculez.";
                this.setTitle(getString(R.string.app_name_fr));
                break;
            case 4:
                txt3.setText("Risultato");
                rs1="Non male";
                rs2="Bene";
                rs3="Eccellente";
                rs4="C\'è qualche dubbio? Stai oscillando.";
                this.setTitle(getString(R.string.app_name_it));
                break;
            case 5:
                txt3.setText("Ergebnis");
                rs1="Nicht schlecht";
                rs2="Gut";
                rs3="Exzellent";
                rs4="Gibt es irgendwelche Zweifel? Du rockst.";
                this.setTitle(getString(R.string.app_name_de));
                break;
            case 6:
                txt3.setText("Resultado");
                rs1="Nada mal";
                rs2="Bom";
                rs3="Excelente";
                rs4="Há alguma dúvida? Você está arrasando.";
                this.setTitle(getString(R.string.app_name_pt));
                break;
            case 7:
                txt3.setText("Результат");
                rs1="Неплохо";
                rs2="Хороший";
                rs3="Отличный";
                rs4="Есть ли сомнения? Вы качаетесь.";
                this.setTitle(getString(R.string.app_name_ru));
                break;
            default:
                txt3.setText("Result");
                rs1="Not Bad";
                rs2="Good";
                rs3="Excellent";
                rs4="Is there any doubt? You are rocking.";
                this.setTitle(getString(R.string.app_name));
                break;
        }

    }

}
