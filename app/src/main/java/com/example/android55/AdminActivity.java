package com.example.android55;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android55.faculty.UpdateFaculty;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
     CardView uploadNotice,addGalleryImage,addpdf,addfaculty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        context = this;
        uploadNotice = findViewById(R.id.addnotice);
        addGalleryImage = findViewById(R.id.addGalleryImage);
        addpdf = findViewById(R.id.addpdf);
        addfaculty = findViewById(R.id.addfaculty);

        addpdf.setOnClickListener(this);
        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addfaculty.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.keluar, menu);
        MenuItem item = menu.findItem(R.id.logout);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("Are you sure, you want to quit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                finish();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                return true;
            }
        });

        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.addnotice:
                intent= new Intent(AdminActivity.this,UploadNotice.class);
                startActivity(intent);
                break;
            case R.id.addGalleryImage:
                intent= new Intent(AdminActivity.this,UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addpdf:
                intent= new Intent(AdminActivity.this,UploadpdfActivity.class);
                startActivity(intent);
                break;
            case R.id.addfaculty:
                intent= new Intent(AdminActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;

        }
    }
}