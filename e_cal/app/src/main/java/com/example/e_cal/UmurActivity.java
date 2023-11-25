package com.example.e_cal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UmurActivity extends AppCompatActivity {

    private Button btn_umur;
    private EditText editText;
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umur);

        btn_umur = (android.widget.Button) findViewById(R.id.buttonU);
        editText = findViewById(R.id.editTextU);

        btn_umur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String inputTextU = editText.getText().toString();

                if (inputTextU.isEmpty()) {
                    showAlertDialog(R.layout.error_dialog);
                } else {
                    //ambil tinggi badan, berat badan, gender
                    Intent intentTB = getIntent();
                    String gender = intentTB.getStringExtra("GENDER_INPUT");
                    String inputTextBB = intentTB.getStringExtra("BB_INPUT");
                    String inputTextTB = intentTB.getStringExtra("TB_INPUT");

                    Intent intent = new Intent(UmurActivity.this, MainActivity.class);
                    intent.putExtra("GENDER_INPUT", gender);
                    intent.putExtra("BB_INPUT", inputTextBB);
                    intent.putExtra("TB_INPUT", inputTextTB);
                    intent.putExtra("UMUR_INPUT", inputTextU);

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