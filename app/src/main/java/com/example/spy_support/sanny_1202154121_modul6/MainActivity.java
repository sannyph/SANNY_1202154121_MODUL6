package com.example.spy_support.sanny_1202154121_modul6;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {

    private FirebaseAuth mAuth;

    EditText mEmail;
    EditText mPass;
    Button mDaftar;
    Button mMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmail = findViewById(R.id.editText);
        mPass = findViewById(R.id.editText2);
        mDaftar = findViewById(R.id.daftar);
        mMasuk = findViewById(R.id.masuk);

        mAuth = FirebaseAuth.getInstance();
    }


    public void login(View view) {

    }

    public void daftar(View view) {
        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pass = mPass.getText().toString();

                if (email.equals("")){
                    mEmail.setError("Required");
                    return;
                }

                if (pass.equals("")){
                    mPass.setError("Required");
                    return;

                }
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    assert user != null;
                                    Toast.makeText(MainActivity.this, "Regis berhasil", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(MainActivity.this, "Regis GAGAL", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        mMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String pass = mPass.getText().toString();

                if (email.equals("")){
                    mEmail.setError("Required");
                    return;
                }

                if (pass.equals("")){
                    mPass.setError("Required");
                    return;

                }
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Log.d("MainActivity", "sukses");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    loginUpdate(user);
                                }else {
                                    Log.d("MainActivity", "gagal", task.getException());
                                    loginUpdate(null);
                                }
                            }
                        });
            }
        });

    }
    private void loginUpdate(FirebaseUser user){
        if (user != null){
            Toast.makeText(MainActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(MainActivity.this, "GAGAL", Toast.LENGTH_SHORT).show();
    }
}