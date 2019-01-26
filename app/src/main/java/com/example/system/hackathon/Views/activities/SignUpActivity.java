package com.example.system.hackathon.Views.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.VideoView;

import com.example.system.hackathon.R;
import com.example.system.hackathon.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mehdi.sakout.fancybuttons.FancyButton;

public class SignUpActivity extends AppCompatActivity {

    EditText userNameET, passwordET, emailET;
    String username , password , email;
    FancyButton signUpBTN;
    Spinner languageSpn;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    VideoView videoView;

    @Override
    protected void onStart() {
        super.onStart();
        videoView.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar toolbar = getSupportActionBar();
        toolbar.hide();
        setContentView(R.layout.activity_sign_up_);

         videoView = findViewById(R.id.landing_activity_videoview_landing_video);
        //setting up video view
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sing_in_vid);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        userNameET = findViewById(R.id.signUp_UserNameET);
        passwordET = findViewById(R.id.signUp_PasswordET);
        emailET = findViewById(R.id.emailET);
        signUpBTN = findViewById(R.id.signUpBTN);
        languageSpn = findViewById(R.id.signUp_LanguageSpn);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        ArrayAdapter languageAdapter = ArrayAdapter.createFromResource(this,R.array.languages
                ,android.R.layout.simple_spinner_item);
        languageAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        languageSpn.setAdapter(languageAdapter);

        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = userNameET.getText().toString().trim();
                password = passwordET.getText().toString().trim();
                email = emailET.getText().toString().trim();

                if(!username.isEmpty()){
                    if (!email.isEmpty()){
                        if (checkEmail(email)){
                            if (password.length()>=6){

                                firebaseAuth.createUserWithEmailAndPassword(email,password)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()){
                                                    Users users = new Users(email,username,"User",
                                                            languageSpn.getSelectedItemPosition(),
                                                            firebaseAuth.getUid());
                                                    firebaseFirestore.collection("Users")
                                                            .document(firebaseAuth.getCurrentUser().getUid())
                                                            .set(users);
                                                    Intent intent = new Intent(SignUpActivity.this,
                                                            UserHomeActivity.class);
                                                    startActivity(intent);
                                                }else
                                                    Toast.makeText(SignUpActivity.this,
                                                            getString(R.string.sign_up_failed)
                                                            ,Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }else
                                passwordET.setError("Password should be at least 6 digits");
                        }else
                            emailET.setError(getString(R.string.email_not_formatted));
                    }else
                        emailET.setError(getString(R.string.empty_email));
                }else
                    userNameET.setError(getString(R.string.enter_username));

            }
        });


    }

    private boolean checkEmail(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
