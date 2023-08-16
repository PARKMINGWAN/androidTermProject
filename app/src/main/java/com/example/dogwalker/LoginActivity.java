package com.example.dogwalker;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;
    private EditText etEmail,etPass,etName;
    private Button btnCancel,btnLogin;

/*
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 2;
    private boolean showOneTapUI = true;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    // ...
                } catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case CommonStatusCodes.CANCELED:
                            Log.d("TAG", "One-tap dialog was closed.");
                            // Don't re-prompt the user.
                            showOneTapUI = false;
                            break;
                        case CommonStatusCodes.NETWORK_ERROR:
                            Log.d("TAG", "One-tap encountered a network error.");
                            // Try again or just ignore.
                            break;
                        default:
                            Log.d("TAG", "Couldn't get credential from result."
                                    + e.getLocalizedMessage());
                            break;
                    }
                }
                break;
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
                         @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                // Automatically sign in when exactly one credential is retrieved.
                .setAutoSelectEnabled(true)
                .build();
        BeginSignInRequest  signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_web_client_id))
                        // Only show accounts previously used to sign in.
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        SignInCredential googleCredential = oneTapClient.getSignInCredentialFromIntent(intent);
        String idToken = googleCredential.getGoogleIdToken();
        if (idToken !=  null) {
            // Got an ID token from Google. Use it to authenticate
            // with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("login success", "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("google Login fail", "signInWithCredential:failure", task.getException());
                            }
                        }
                    });
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etUserId);
        etPass = findViewById(R.id.etUserPass);
        mAuth = FirebaseAuth.getInstance();
      btnLogin =findViewById(R.id.btnSignIn);



      btnLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String strEmail = etEmail.getText().toString();
              String StrPass = etPass.getText().toString();

              mAuth.signInWithEmailAndPassword(strEmail, StrPass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful())
                      {
                          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                          finish();
                      }
                      else {

                      }
                  }
              });






          }
      });



    }



}