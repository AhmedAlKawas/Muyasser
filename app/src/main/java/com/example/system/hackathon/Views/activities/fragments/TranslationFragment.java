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
import com.example.system.hackathon.presenters.HealthAdapter;
import com.example.system.hackathon.presenters.TranslationAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TranslationFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    CollectionReference collectionReference;
    ArrayList mTranslate;
    TranslationAdapter translationAdapter;

    public TranslationFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translation,container,false);
        recyclerView = view.findViewById(R.id.translationRV);
        mTranslate = new ArrayList();

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection("Users");

        collectionReference.whereEqualTo("rule","Translator").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot : task.getResult()){
                                Users users = documentSnapshot.toObject(Users.class);
                                Users users1 = new Users();
                                users1.setUsername(users.getUsername());
                                users1.setId(users.getId());
                                mTranslate.add(users1);
                            }
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            translationAdapter = new TranslationAdapter(mTranslate , getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(translationAdapter);
                        }else
                            Log.e("error",task.getException().toString());
                    }
                });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(translationAdapter);
        return view;
    }
}
