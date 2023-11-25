package com.example.e_cal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DaftarMenuActivity extends AppCompatActivity {
    private Button btn_back;
    private Dialog mDialog;
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);

        btn_back = findViewById(R.id.back);
        ImageView addAyam = findViewById(R.id.addFood1);

        //back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //add menu sarapan
        mDialog = new Dialog(this);
        // 01 dada ayam
        addAyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String time = getIntent().getStringExtra("TIME");

                mDialog.setContentView(R.layout.activity_popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

                Button addFood = mDialog.findViewById(R.id.tambahMakan);
                EditText porsi = mDialog.findViewById(R.id.porsiMakanan);

                addFood.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String jumlahMakanan = porsi.getText().toString();

                        if (jumlahMakanan.isEmpty()) {
                            showAlertDialog(R.layout.error_dialog);
                        } else {
                            Intent intent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private void showAlertDialog(int myLayout) {

        builderDialog = new AlertDialog.Builder(this);
        View layoutView = getLayoutInflater().inflate(myLayout, null);

        Button dialogButton = layoutView.findViewById(R.id.buttonOK);
        builderDialog.setView(layoutView);
        alertDialog = builderDialog.create();
        alertDialog.show();

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}