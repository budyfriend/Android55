package com.example.android55.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android55.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton addfab;
    private RecyclerView mcaDepartment,mbaDepartment,eseDepartment,cseDepartment;
    private LinearLayout csenodata,mcanodata,mbanodata,esenodata;
    private List<TeacherData> list1,list2,list3,list4;
    private DatabaseReference reference , dbref;
    private TeacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);

        mcaDepartment = findViewById(R.id.mcaDepartment);
        mbaDepartment = findViewById(R.id.mbaDepartment);
        eseDepartment = findViewById(R.id.eseDepartment);
        cseDepartment = findViewById(R.id.cseDepartment);

        csenodata = findViewById(R.id.csenodata);
        mcanodata = findViewById(R.id.mcanodata);
        mbanodata = findViewById(R.id.mbanodata);
        esenodata = findViewById(R.id.esenodata);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");

        mcaDepartment();
        mbaDepartment();
        cseDepartment();
        eseDepartment();


        addfab = findViewById(R.id.addfab);

        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateFaculty.this,addteacher.class));
            }
        });
    }

    private void mcaDepartment() {
        dbref = reference.child("MCA");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot  dataSnapshot) {
                list1 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    mcanodata.setVisibility(View.VISIBLE);
                    mcanodata.setVisibility(View.GONE);
                }else{
                    mcanodata.setVisibility(View.GONE);
                    mcanodata.setVisibility(View.VISIBLE);

                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    mcaDepartment.setHasFixedSize(true);
                    mcaDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list1, UpdateFaculty.this);
                    mcaDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void mbaDepartment() {
        dbref = reference.child("MBA");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot  dataSnapshot) {
                list2 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    mbanodata.setVisibility(View.VISIBLE);
                    mbanodata.setVisibility(View.GONE);
                }else{
                    mbanodata.setVisibility(View.GONE);
                    mbanodata.setVisibility(View.VISIBLE);

                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    mbaDepartment.setHasFixedSize(true);
                    mbaDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list2, UpdateFaculty.this);
                    mbaDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void cseDepartment() {
        dbref = reference.child("CSE");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot  dataSnapshot) {
                list1 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    csenodata.setVisibility(View.VISIBLE);
                    csenodata.setVisibility(View.GONE);
                }else{
                    csenodata.setVisibility(View.GONE);
                    csenodata.setVisibility(View.VISIBLE);

                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    cseDepartment.setHasFixedSize(true);
                    cseDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list3, UpdateFaculty.this);
                    cseDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void eseDepartment() {
        dbref = reference.child("ESE");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot  dataSnapshot) {
                list4 = new ArrayList<>();
                if(!dataSnapshot.exists()){
                    esenodata.setVisibility(View.VISIBLE);
                    esenodata.setVisibility(View.GONE);
                }else{
                    esenodata.setVisibility(View.GONE);
                    esenodata.setVisibility(View.VISIBLE);

                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    eseDepartment.setHasFixedSize(true);
                    eseDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter = new TeacherAdapter(list4, UpdateFaculty.this);
                    eseDepartment.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UpdateFaculty.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}