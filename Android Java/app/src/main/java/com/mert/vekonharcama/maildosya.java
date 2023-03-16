package com.mert.vekonharcama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class maildosya extends AppCompatActivity {
    EditText eposta;
    String eposta1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maildosya);

        eposta = findViewById(R.id.mail);
        Button button = findViewById(R.id.devam);
        sharedPreferences = this.getSharedPreferences("com.mert.vekonharcama", Context.MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eposta1 = eposta.getText().toString();
                if (eposta1.equals("")){
                    Toast.makeText(maildosya.this,"Lütfen E postayı Dogru Giriniz.",Toast.LENGTH_LONG);
                }else {
                    sharedPreferences.edit().putString("mail",eposta1).apply();
                    Intent intent = new Intent(maildosya.this,AnaPanel.class);
                    startActivity(intent);
                }
            }
        });



    }
}