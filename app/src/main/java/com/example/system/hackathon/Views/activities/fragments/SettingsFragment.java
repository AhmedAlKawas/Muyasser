package com.example.system.hackathon.Views.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.system.hackathon.R;
import com.example.system.hackathon.Views.activities.MainActivity;
import com.example.system.hackathon.Views.activities.UserHomeActivity;
import com.example.system.hackathon.Views.activities.UserProfile;
import com.google.firebase.auth.FirebaseAuth;

import mehdi.sakout.fancybuttons.FancyButton;

public class SettingsFragment extends Fragment {

    FancyButton signOut;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    CardView cardView;

    public SettingsFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);
        signOut = view.findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });
        cardView = view.findViewById(R.id.myProfileCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), UserProfile.class);
                startActivity(i);
            }
        });
        return view;
    }
}
