package com.example.kdeek.enelchat;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class SignUp extends AppCompatActivity implements View.OnClickListener{


    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    DatabaseReference myRef = mDatabase.getReference("users");


    private EditText mEmailField;
    private EditText mPasswordField;
    EditText fname, lname;
    Spinner gender;
    String email1="";
    String pwd="";
    String fname1="";
    String lname1="";
    String gender1="";
    String fullname = "";

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    // [END declare_auth_listener]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        fname = (EditText) findViewById(R.id.field_fnameSU);
        lname = (EditText) findViewById(R.id.field_lnameSU);
        mEmailField = (EditText) findViewById(R.id.field_emailSU);
        mPasswordField = (EditText) findViewById(R.id.field_passwordSU);
        findViewById(R.id.email_sign_in_buttonSU).setOnClickListener(this);
        findViewById(R.id.cancel_buttonSU).setOnClickListener(this);
        gender = (Spinner)findViewById(R.id.gender);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender1 = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(),
                        "Please select a type.",
                        Toast.LENGTH_SHORT).show();

            }
        });


        mAuth = FirebaseAuth.getInstance();


    }



    private void createAccount(String email, String password) {
        fname1 = fname.getText().toString();
        lname1 = lname.getText().toString();
        email1 = mEmailField.getText().toString();
        pwd = mPasswordField.getText().toString();

        fullname = fname1+" "+lname1;



        Log.d("SignUp", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("SignUp", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Intent create = new Intent();
                            setResult(Activity.RESULT_OK,create);
                            User user = new User(fname1,gender1,"",fullname,lname1,email1,pwd);
                            user.setID(mAuth.getCurrentUser().getUid());

                            myRef.child(mAuth.getCurrentUser().getUid().toString()).setValue(user);
                            finish();
                        }

                    }
                });

    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }
        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.email_sign_in_buttonSU) {
            createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.cancel_buttonSU) {
            Intent create = new Intent();
            setResult(Activity.RESULT_CANCELED,create);
            finish();
        }
    }
}