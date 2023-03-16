package com.mert.vekonharcama;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class AnaPanel extends AppCompatActivity {
    TextView isadi,firmaadi,avansialan,verilenavans;
    String isadi1,firmaadi1,avansialan1,verilenavans1;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_panel);
        isadi = findViewById(R.id.isadi);
        firmaadi = findViewById(R.id.firmaadi);
        avansialan = findViewById(R.id.avansalan);
        verilenavans = findViewById(R.id.verilenavans);
        sharedPreferences = this.getSharedPreferences("com.mert.vekonharcama", Context.MODE_PRIVATE);




    }

    public void ileri(View view){
        isadi1 = isadi.getText().toString();
        firmaadi1 = firmaadi.getText().toString();
        avansialan1 = avansialan.getText().toString();
        verilenavans1 = verilenavans.getText().toString();
        if (isadi1.equals("") || firmaadi1.equals("") || avansialan1.equals("") || verilenavans1.equals("")){
            Toast.makeText(AnaPanel.this,"Lutfen Bilgileri Eksiksiz Giriniz.",Toast.LENGTH_LONG).show();
        }else {
            sharedPreferences.edit().putString("isadi",isadi1).apply();
            sharedPreferences.edit().putString("firmaadi",firmaadi1).apply();
            sharedPreferences.edit().putString("avansalan",avansialan1).apply();
            sharedPreferences.edit().putString("verilenavans",verilenavans1).apply();
            Toast.makeText(AnaPanel.this,"Fis Ekranina Yonlendiriliyorsun....",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AnaPanel.this,fisyaz.class);
            startActivity(intent);
        }
    }


}