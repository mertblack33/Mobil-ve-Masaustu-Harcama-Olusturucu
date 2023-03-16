package com.mert.vekonharcama;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class anaekran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anaekran);


    }

    public void yeni(View view) {
        Intent intent = new Intent(anaekran.this,maildosya.class);
        startActivity(intent);
        Toast.makeText(anaekran.this,"Lutfen Bilgileri Eksiksiz Giriniz.",Toast.LENGTH_LONG).show();
    }
    public void eski(View view){
        Toast.makeText(anaekran.this,"Suan Aktif Degildir.",Toast.LENGTH_LONG).show();
    }
}