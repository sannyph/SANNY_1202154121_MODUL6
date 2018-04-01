package com.example.spy_support.sanny_1202154121_modul6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText regPassword, regEmail ;
    Button btnRegis;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        regPassword = findViewById(R.id.etPass);
        regEmail = findViewById(R.id.etEmail);
        btnRegis = findViewById(R.id.btnRegister);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regFirebase();
            }
        });
    }

    private void regFirebase() {
        String email = regEmail.getText().toString();
        String pass = regPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "coba periksa kembali email yang anda masukan!", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "coba periksa kembali password yang anda masukan!", Toast.LENGTH_SHORT).show();
        }

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this, "Registrasi gagal!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Register.this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
