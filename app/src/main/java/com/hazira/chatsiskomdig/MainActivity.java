package com.hazira.chatsiskomdig;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import co.chatsdk.core.error.ChatSDKException;
import co.chatsdk.core.session.ChatSDK;
import co.chatsdk.core.session.Configuration;
import co.chatsdk.firebase.FirebaseNetworkAdapter;
import co.chatsdk.firebase.file_storage.FirebaseFileStorageModule;
import co.chatsdk.firebase.push.FirebasePushModule;
import co.chatsdk.ui.manager.BaseInterfaceAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context context = getApplicationContext();

        // Create a new configuration
        Configuration.Builder builder = new Configuration.Builder(context);

        // Perform any configuration steps (optional)
        builder.firebaseRootPath("prod");

        // Initialize the Chat SDK
        try {
            ChatSDK.initialize(builder.build(), new FirebaseNetworkAdapter(), new BaseInterfaceAdapter(context));
        } catch (ChatSDKException e) {
        }

        // File storage is needed for profile image upload and image messages
        FirebaseFileStorageModule.activate();
        FirebasePushModule.activate();
        //FirebaseUIModule.activate(context, GoogleAuthProvider.PROVIDER_ID, PhoneAuthProvider.PROVIDER_ID);

        final Button buttonStartQuiz = findViewById(R.id.button_start_chat);
        buttonStartQuiz.setOnClickListener(viewM1Q1 -> startChat());
    }

    private void startChat() {
        Intent intent = new Intent(this, co.chatsdk.ui.login.LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

