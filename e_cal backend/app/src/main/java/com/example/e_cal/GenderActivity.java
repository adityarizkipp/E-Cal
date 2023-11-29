package com.example.e_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GenderActivity extends AppCompatActivity {

    private Button btn_pria;
    private Button btn_wanita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        btn_pria = findViewById(R.id.buttonPria);
        btn_wanita = findViewById(R.id.buttonWanita);

        btn_pria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "pria";

                Intent intent = new Intent(GenderActivity.this, beratBadanActivity.class);
                intent.putExtra("GENDER_INPUT", gender);
                startActivity(intent);
                finish();
            }
        });

        btn_wanita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "wanita";

                Intent intent = new Intent(GenderActivity.this, beratBadanActivity.class);
                intent.putExtra("GENDER_INPUT", gender);
                startActivity(intent);
            }
        });
    }
}