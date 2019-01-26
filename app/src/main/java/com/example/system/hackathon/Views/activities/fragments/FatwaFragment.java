package com.example.system.hackathon.Views.activities.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Users;
import com.example.system.hackathon.presenters.FatwaAdapter;
import com.example.system.hackathon.presenters.HealthAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FatwaFragment extends Fragment {

    ArrayList fatwaArray;
    CollectionReference collectionReference;
    RecyclerView fatwaRV;
    FatwaAdapter fatwaAdapter;
    FirebaseFirestore firebaseFirestore;

    public FatwaFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fatwa,container,false);
        fatwaArray = new ArrayList();
        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("Users");
        collectionReference.whereEqualTo("rule","Fatwa").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult()){
                                Users users = documentSnapshot.toObject(Users.class);
                                Users users1 = new Users();
                                users1.setUsername(users.getUsername());
                                users1.setId(users.getId());
                                fatwaArray.add(users1);
                                Log.e("fatwa",documentSnapshot.getData().toString());
                            }
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            fatwaAdapter = new FatwaAdapter(fatwaArray , getContext());
                            fatwaRV.setLayoutManager(layoutManager);
                            fatwaRV.setAdapter(fatwaAdapter);
                        }else
                            Log.e("error",task.getException().toString());
                    }
                });

        fatwaRV = view.findViewById(R.id.fatwaRV);
        return view;
    }
}
