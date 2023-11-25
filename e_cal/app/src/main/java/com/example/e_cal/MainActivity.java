package com.example.e_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.w3c.dom.Text;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBarConsumed;
    private TextView textConsumed;
    private TextView textRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ambil data
        Intent intentU = getIntent();
        String gender = intentU.getStringExtra("GENDER_INPUT");
        String inputTextBB = intentU.getStringExtra("BB_INPUT");
        String inputTextTB = intentU.getStringExtra("TB_INPUT");
        String inputTextU = intentU.getStringExtra("UMUR_INPUT");

        TextView sarapan = findViewById(R.id.TargetFixSarapan);
        TextView lunch = findViewById(R.id.TargetFixLunch);
        TextView dinner =findViewById(R.id.TargetFixDinner);

        int bb = Integer.parseInt(inputTextBB);
        int tb = Integer.parseInt(inputTextTB);
        int umr = Integer.parseInt(inputTextU);

        progressBarConsumed = findViewById(R.id.progressBar);
        textRemaining = findViewById(R.id.CaloriesLeftNumb);
        textConsumed = findViewById(R.id.CaloriesEatenNumb);

        //target fix sarapan, makan siang, makan malam
        if (gender.equals("pria")) {
            int kalori = (int) Math.ceil(66.5+(13.75*bb)+(5.003*tb)-(6.75*umr));

            int sarapanPria = (int) Math.ceil(0.25*kalori);
            int lunchPria = (int) Math.ceil(0.4*kalori);
            int dinnerPria = (int) Math.ceil(0.35*kalori);

            sarapan.setText(Integer.toString(sarapanPria));
            lunch.setText(Integer.toString(lunchPria));
            dinner.setText((Integer.toString(dinnerPria)));


        } else if (gender.equals("wanita")) {
            int kalori = (int) Math.ceil(655.1+(9.563*bb)+(1.850*tb)-(4.676*umr));

            int sarapanWanita= (int) Math.ceil(0.25*kalori);
            int lunchWanita = (int) Math.ceil(0.4*kalori);
            int dinnerWanita = (int) Math.ceil(0.35*kalori);

            sarapan.setText(Integer.toString(sarapanWanita));
            lunch.setText(Integer.toString(lunchWanita));
            dinner.setText(Integer.toString(dinnerWanita));
        }

        //kalori yang di konsumsi
        // 01 Sarapan

        //progressbar setting

        //tombol sarapan, makan siang, makan malam
        Button btn_sarapan = findViewById(R.id.buttonSarapan);
        Button btn_lunch = findViewById(R.id.buttonLunch);
        Button btn_dinner = findViewById(R.id.buttonDinner);

        btn_sarapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = "sarapan";

                Bundle info = new Bundle();
                Intent intent = new Intent(MainActivity.this, DaftarMenuActivity.class);
                info.putString("TIME",time);
                intent.putExtras(info);
                startActivity(intent);
            }
        });

        btn_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = "lunch";

                Intent intent = new Intent(MainActivity.this, DaftarMenuActivity.class);
                intent.putExtra("TIME", time);
                startActivity(intent);
            }
        });

        btn_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = "dinner";

                Intent intent = new Intent(MainActivity.this, DaftarMenuActivity.class);
                intent.putExtra("TIME", time);
                startActivity(intent);
            }
        });
    }
}