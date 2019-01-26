package com.example.system.hackathon.Views.activities.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Feeds;
import com.example.system.hackathon.presenters.FeedsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FeedsFragment extends Fragment {

    ArrayList mFeeds;
    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = firebaseFirestore.collection("Feeds");


    public FeedsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeds,container,false);
        mFeeds = new ArrayList();
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        Feeds feeds = new Feeds();
                        Feeds feeds1 = queryDocumentSnapshot.toObject(Feeds.class);
                        feeds.setTitle(feeds1.getTitle());
                        feeds.setDescription(feeds1.getDescription());
                        mFeeds.add(feeds);
                    }
                    FeedsAdapter feedsAdapter = new FeedsAdapter(mFeeds);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(feedsAdapter);
                }else
                    Log.e("errror",task.getException().toString());
            }


        });

        recyclerView = view.findViewById(R.id.feedsRecycler);
        FeedsAdapter feedsAdapter = new FeedsAdapter(mFeeds);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(feedsAdapter);
        return view;
    }
}
