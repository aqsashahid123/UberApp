package uberapp.itpvt.com.uberapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverActivity extends AppCompatActivity {
    EditText etEmail,etPassword;
    Button btnLogin;
    Button btnRegister;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        etEmail = (EditText) findViewById(R.id.etRiderEmail);
        etPassword = (EditText) findViewById(R.id.etRiderPasswprd);
        btnLogin = (Button) findViewById(R.id.btnLoginAsrIDER);
        btnRegister = (Button) findViewById(R.id.btnRegisterAsDriver);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){

                    Intent intent = new Intent(DriverActivity.this,DriverMapActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final  String pssword = etPassword.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email,pssword).addOnCompleteListener(DriverActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){

                            Toast.makeText(DriverActivity.this,"Sign up error",Toast.LENGTH_SHORT).show();

                        }

                        else {

                            String user_id = firebaseAuth.getCurrentUser().getUid();
                            DatabaseReference current_user = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(user_id);
                            current_user.setValue(true);
                        }

                    }
                });


            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final  String pssword = etPassword.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,pssword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){

                            Toast.makeText(DriverActivity.this,"Sign in error",Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
