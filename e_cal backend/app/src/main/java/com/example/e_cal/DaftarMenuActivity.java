package com.example.e_cal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DaftarMenuActivity extends AppCompatActivity {
    private Button btn_back;
    private Dialog mDialog;
    AlertDialog.Builder builderDialog;
    AlertDialog alertDialog;
    private int kaloriKonsumsiPagi;
    private int kaloriKonsumsiSiang;
    private int kaloriKonsumsiMalam;
    private int kaloriKonsumsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menu);

        //ambil waktu
        String time = getIntent().getStringExtra("TIME");

        btn_back = findViewById(R.id.back);
        ImageView addAyam = findViewById(R.id.addFood1);
        ImageView addOat = findViewById(R.id.addFood2);
        ImageView addCorn = findViewById(R.id.addFood3);
        ImageView addSosis = findViewById(R.id.addFood4);
        ImageView addTelur = findViewById(R.id.addFood5);
        ImageView addIkan = findViewById(R.id.addFood6);
        ImageView addWortel =findViewById(R.id.addFood7);

        //back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mDialog = new Dialog(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        kaloriKonsumsiPagi = sharedPreferences.getInt("kaloriKonsumsiPagi", 0);
        kaloriKonsumsiSiang = sharedPreferences.getInt("kaloriKonsumsiSiang", 0);
        kaloriKonsumsiMalam = sharedPreferences.getInt("kaloriKonsumsiMalam", 0);

        //tambah makan
        if(!time.isEmpty()) {

            //dada ayam
            addAyam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);

                    namaMakanan.setText("Dada Ayam");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Dada Ayam";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 1.64);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });

            addOat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);
                    TextView infoMakanan = mDialog.findViewById(R.id.infoGram);

                    infoMakanan.setText("91 kkal • 100 gram");
                    namaMakanan.setText("Oat Meal Instan");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Oat Meal Instan";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 0.91);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });

            addCorn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);
                    TextView infoMakanan = mDialog.findViewById(R.id.infoGram);

                    infoMakanan.setText("360 kkal • 100 gram");
                    namaMakanan.setText("Corn Flakes");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Corn Flakes";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 3.6);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });

            addSosis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);
                    TextView infoMakanan = mDialog.findViewById(R.id.infoGram);

                    infoMakanan.setText("325 kkal • 100 gram");
                    namaMakanan.setText("Sosis Sapi");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Sosis Sapi";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 3.25);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });

            addTelur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);
                    TextView infoMakanan = mDialog.findViewById(R.id.infoGram);

                    infoMakanan.setText("201 kkal • 100 gram");
                    namaMakanan.setText("Telur Mata Sapi");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Telur Mata Sapi";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 2.01);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });

            addIkan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);
                    TextView infoMakanan = mDialog.findViewById(R.id.infoGram);

                    infoMakanan.setText("208 kkal • 100 gram");
                    namaMakanan.setText("Ikan Sarden Kaleng");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Ikan Sarden Kaleng";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 2.08);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });

            addWortel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mDialog.setContentView(R.layout.activity_popup);
                    mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mDialog.show();

                    Button addFood = mDialog.findViewById(R.id.tambahMakan);
                    EditText porsi = mDialog.findViewById(R.id.porsiMakanan);
                    TextView namaMakanan = mDialog.findViewById(R.id.namaMakanan);
                    TextView infoMakanan = mDialog.findViewById(R.id.infoGram);

                    infoMakanan.setText("41 kkal • 100 gram");
                    namaMakanan.setText("Wortel");

                    addFood.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String jumlahMakanan = porsi.getText().toString();
                            String namaMakanan = "Wortel";
                            int totalMakanan = (int) Math.ceil(Integer.parseInt(jumlahMakanan) * 0.41);

                            if (jumlahMakanan.isEmpty()) {
                                showAlertDialog(R.layout.error_dialog);
                            } else {

                                if (time.equals("sarapan")) {
                                    kaloriKonsumsiPagi += totalMakanan;
                                } else if (time.equals("lunch")) {
                                    kaloriKonsumsiSiang += totalMakanan;
                                } else if (time.equals("dinner")) {
                                    kaloriKonsumsiMalam += totalMakanan;
                                }

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("kaloriKonsumsiPagi", kaloriKonsumsiPagi);
                                editor.putInt("kaloriKonsumsiSiang", kaloriKonsumsiSiang);
                                editor.putInt("kaloriKonsumsiMalam", kaloriKonsumsiMalam);
                                editor.apply();

                                Intent resultIntent = new Intent(DaftarMenuActivity.this, MainActivity.class);
                                resultIntent.putExtra("KONSUMSI", totalMakanan);
                                resultIntent.putExtra("MAKANAN", namaMakanan);
                                setResult(RESULT_OK, resultIntent);
                                finish();
                            }
                        }
                    });
                }
            });
        }
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