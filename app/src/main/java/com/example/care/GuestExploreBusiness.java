package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GuestExploreBusiness extends AppCompatActivity implements View.OnClickListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private static Activity thisActivity = null;

    private RecyclerView recyclerView;
    private Button accountHome;

    private ArrayList<String> businessNames = new ArrayList<>();
    private UserModel userModel;
    private GuestAccount guest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_explore_business);

        this.userModel = new UserModel(this);
        guest = getIntent().getExtras().getParcelable("User");
        getData();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAccountHome:
                Intent intent = new Intent(GuestExploreBusiness.this, GuestHome.class);
                intent.putExtra("User", guest);
                startActivity(intent);
                break;
        }
    }


    public void setUI(){
        recyclerView = findViewById(R.id.rvExplore);
        accountHome = findViewById(R.id.btnAccountHome);
        accountHome.setOnClickListener(this);

        RVOfficerAdapter adapter = new RVOfficerAdapter(businessNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void getData() {
        db.collection("Businesses").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    processData(list);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    public void processData(ArrayList<String> list){
        businessNames = list;
        setUI();
    }

}