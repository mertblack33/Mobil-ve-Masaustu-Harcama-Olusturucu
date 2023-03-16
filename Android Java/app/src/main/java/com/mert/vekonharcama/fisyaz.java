package com.mert.vekonharcama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class fisyaz extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String gider,isinadi,firmaadi1,avansialan,verilenavans,mail;
    String ffistarihi,fbelgeno,ffirmaadi,fkisisayisi,ffiyat;
    EditText fistarihi,belgeno,firmaadi,kisisayisi,fiyat;
    TextView fisk;
    int fis =1;
    double toplam = 0;
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    HashMap<String , Object> postData = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisyaz);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.mert.vekonharcama", Context.MODE_PRIVATE);
        Spinner spinner = findViewById(R.id.gider);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gidercesiti, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        fistarihi = findViewById(R.id.fistarihi);
        belgeno = findViewById(R.id.belge);
        firmaadi = findViewById(R.id.firma);
        kisisayisi = findViewById(R.id.kisi1);
        fiyat = findViewById(R.id.tutar);
        fisk = findViewById(R.id.fisk);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        //Üst Gövde Elementi
        isinadi = sharedPreferences.getString("isadi","null");
        firmaadi1 = sharedPreferences.getString("firmaadi","null");
        avansialan = sharedPreferences.getString("avansalan","null");
        verilenavans = sharedPreferences.getString("verilenavans","null");
        mail = sharedPreferences.getString("mail","null");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Fiş Elementleri
        gider = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    public void ileri1(View view){
        ffistarihi = fistarihi.getText().toString();
        fbelgeno = belgeno.getText().toString();
        ffirmaadi = firmaadi.getText().toString();
        fkisisayisi = kisisayisi.getText().toString();
        ffiyat = fiyat.getText().toString();

        if(ffistarihi.equals("") || fbelgeno.equals("") || ffirmaadi.equals("") || fkisisayisi.equals("")
        || ffiyat.equals("")){
            Toast.makeText(fisyaz.this,"LÜTFEN EKSİKSİZ GİRİNİZ",Toast.LENGTH_LONG).show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Devam Etmek Istiyormusun");
            builder.setMessage("Sonraki Fise Geçmek Istiyormusunuz.");
            builder.setPositiveButton("Devam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    double toplamfiyat = Double.parseDouble(fiyat.getText().toString());
                    toplam += toplamfiyat;
                    postData.put("fistarih"+fis,ffistarihi);
                    postData.put("fisbelgeno"+fis,fbelgeno);
                    postData.put("fisfirmaadi"+fis,ffirmaadi);
                    postData.put("fiskisisayisi"+fis,fkisisayisi);
                    postData.put("fisfiyat"+fis,ffiyat);
                    postData.put("fisgidercesiti"+fis,gider);
                    Toast.makeText(fisyaz.this,""+fis+". Fis Basarili sekilde Kaydedildi.",Toast.LENGTH_LONG).show();
                    fis++;
                    fisk.setText(fis+". FISI GIRIYORSUN");
                    fistarihi.setText("");
                    belgeno.setText("");
                    firmaadi.setText("");
                    kisisayisi.setText("");
                    fiyat.setText("");
                }
            });
            builder.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();

        }

    }



    public void gonder(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Gondermek Istiyormusunuz.");
        alert.setMessage("Toplam Fis Fiyati :"+toplam);
        alert.setPositiveButton("Gonder", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                data();
            }


        });
        alert.show();


    }
    public void data(){
        postData.put("isnumarasi",isinadi);
        postData.put("mail",mail);
        postData.put("firmaadi",firmaadi1);
        postData.put("avansialan",avansialan);
        postData.put("verilenavans",verilenavans+"TL");
        firebaseFirestore.collection(mail).add(postData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(fisyaz.this, "Mail Adresine Basarili bir sekilde Gonderilmistir.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fisyaz.this,anaekran.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(fisyaz.this,"Sunucu Veya Internet Kaynakli Bi Sorun Var.",Toast.LENGTH_LONG).show();
            }
        });

    }

}