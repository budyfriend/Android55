package com.example.android55;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {
    EditText email, password;
    Button loginBtn, gotoRegister;
    boolean valid = true;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressDialog progressDialog;
    Context context;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        context = this;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.loginBtn);
        gotoRegister = findViewById(R.id.gotoRegister);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);


                if (valid) {
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    fAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Login.this, "Loggedin successfully", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        //Extract data from document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "OnSuccess:" + documentSnapshot.getData());
                //Indentify user access level

                if (documentSnapshot.getString("isTeacher") != null) {
                    startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                    finish();
                }
                if (documentSnapshot.getString("isStudent") != null) {
                    startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                    finish();
                }
            }
        });
    }

    public void checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;
        }
    }

    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.getString("isTeacher") != null) {
                        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                        progressDialog.dismiss();
                        finish();
                    }
                    if (documentSnapshot.getString("isStudent") != null) {
                        startActivity(new Intent(getApplicationContext(), StudentActivity.class));
                        progressDialog.dismiss();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    progressDialog.dismiss();
                    finish();
                }
            });
        }
    }
}