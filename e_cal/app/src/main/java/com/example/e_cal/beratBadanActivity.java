package com.example.e_cal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class beratBadanActivity extends AppCompatActivity {

    private Button btn_beratBadan;
    private EditText editText;

    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berat_badan);

        btn_beratBadan = (android.widget.Button) findViewById(R.id.buttonBB);
        editText = findViewById(R.id.editTextBB);

        btn_beratBadan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputTextBB = editText.getText().toString();

                if (inputTextBB.isEmpty()) {
                    showAlertDialog(R.layout.error_dialog);
                } else {
                    //ambil gender
                    Intent intentGen = getIntent();
                    String gender = intentGen.getStringExtra("GENDER_INPUT");

                    Intent intent = new Intent(beratBadanActivity.this, tinggiBadanActivity.class);
                    intent.putExtra("GENDER_INPUT", gender);
                    intent.putExtra("BB_INPUT", inputTextBB);
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