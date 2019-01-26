package com.example.system.hackathon.Views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {

    EditText userName , emailET;
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firebaseFirestore = FirebaseFirestore.getInstance();

        userName = findViewById(R.id.userNameET);
        emailET = findViewById(R.id.emailET);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        emailET.setText(currentUser.getEmail());

        firebaseFirestore.collection("Users").document(currentUser.getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot,FirebaseFirestoreException e) {
                Users users = documentSnapshot.toObject(Users.class);
                userName.setText(users.getUsername());
            }
        });

    }
}
