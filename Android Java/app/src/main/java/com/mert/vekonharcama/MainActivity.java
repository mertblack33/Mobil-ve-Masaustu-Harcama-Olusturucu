package com.mert.vekonharcama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mert.vekonharcama.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    String eposta,sifre;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(MainActivity.this,arapanel.class);
            startActivity(intent);
            finish();
        }
    }
    public void kayit(View view){
        eposta = binding.eposta.getText().toString();
        sifre = binding.pass.getText().toString();
        if (eposta.equals("") || sifre.equals("")){
            Toast.makeText(MainActivity.this, "E POSTAYI VE ŞİFREYİ KONTROL ET KARDEŞİM", Toast.LENGTH_SHORT).show();

        }else {
            firebaseAuth.createUserWithEmailAndPassword(eposta,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,arapanel.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(e.getLocalizedMessage());
                }
            });
        }
    }

    public void giris(View view){
        eposta = binding.eposta.getText().toString();
        sifre = binding.pass.getText().toString();
        if (eposta.equals("") || sifre.equals("")){
            Toast.makeText(MainActivity.this, "E POSTA VEYA ŞİFRE HATALI", Toast.LENGTH_SHORT).show();

        }else {
            firebaseAuth.signInWithEmailAndPassword(eposta,sifre).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent intent = new Intent(MainActivity.this,arapanel.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


