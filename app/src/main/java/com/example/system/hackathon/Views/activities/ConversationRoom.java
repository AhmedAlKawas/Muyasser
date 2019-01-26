package com.example.system.hackathon.Views.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Consultion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Random;

import mehdi.sakout.fancybuttons.FancyButton;

public class ConversationRoom extends AppCompatActivity {

    String id , consultion;
    EditText consultionTV;
    FancyButton sendBTN;
    FirebaseFirestore firebaseFirestore;
    TextView consultationQTV,consultationAnsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation_room);
        sendBTN = findViewById(R.id.sendBtn);
        consultionTV = findViewById(R.id.consultionInput);
        consultationQTV = findViewById(R.id.consultationTV);
        firebaseFirestore = FirebaseFirestore.getInstance();
        Intent i = getIntent();
        id = i.getStringExtra("id");

        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultion = consultionTV.getText().toString();
                Consultion c = new Consultion(FirebaseAuth.getInstance().getUid(),id,consultion,"");
                String e = String.valueOf(new Random().nextInt(9999999));
                firebaseFirestore.collection("Consulations").document(e).set(c);
                consultionTV.setText("");
                consultationQTV.setText(consultion);
                consultationQTV.setPadding(5,5,5,5);
            }
        });

    }
}
