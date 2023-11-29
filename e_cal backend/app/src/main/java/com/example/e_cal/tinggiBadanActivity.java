package com.example.e_cal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tinggiBadanActivity extends AppCompatActivity {

    private Button btn_tinggiBadan;
    private EditText editText;
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinggi_badan);

        btn_tinggiBadan = (android.widget.Button) findViewById(R.id.buttonTB);
        editText = findViewById(R.id.editTextTB);

        btn_tinggiBadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTextTB = editText.getText().toString();

                if (inputTextTB.isEmpty()) {
                    showAlertDialog(R.layout.error_dialog);
                } else {
                    //ambil berat badan, gender
                    Intent intentBB = getIntent();
                    String gender = intentBB.getStringExtra("GENDER_INPUT");
                    String inputTextBB = intentBB.getStringExtra("BB_INPUT");

                    //pindah activity
                    Intent intent = new Intent(tinggiBadanActivity.this, UmurActivity.class);
                    intent.putExtra("GENDER_INPUT", gender);
                    intent.putExtra("BB_INPUT", inputTextBB);
                    intent.putExtra("TB_INPUT", inputTextTB);

                    startActivity(intent);
                    finish();
                }

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