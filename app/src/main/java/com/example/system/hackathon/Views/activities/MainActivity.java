package com.example.system.hackathon.Views.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import com.example.system.hackathon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {

    EditText emailET, passwordET;
    Spinner languageSpn;
    FancyButton signInBtn;
    TextView signUpTV;
    String email, password;
    private FirebaseAuth firebaseAuth;
    VideoView videoView;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(MainActivity.this,UserHomeActivity.class);
            startActivity(intent);
        }
        videoView.start();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar toolbar = getSupportActionBar();
        toolbar.hide();
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoview_landing_video);
        //setting up video view
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sing_in_vid);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        signUpTV = findViewById(R.id.signUpTV);
        signInBtn = findViewById(R.id.signInBTN);
        languageSpn = findViewById(R.id.languageSpn);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);
        firebaseAuth = FirebaseAuth.getInstance();

        ArrayAdapter languageAdapter = ArrayAdapter.createFromResource(this,R.array.languages
            ,android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        languageSpn.setAdapter(languageAdapter);

        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailET.getText().toString().trim();
                password = passwordET.getText().toString().trim();

               firebaseAuth.signInWithEmailAndPassword(email,password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Intent intent= new Intent(MainActivity.this, UserHomeActivity.class);
                           startActivity(intent);
                       }else
                           Toast.makeText(MainActivity.this,task.getException().toString()
                                   ,Toast.LENGTH_SHORT).show();
                   }
               });
            }
        });
    }
}
