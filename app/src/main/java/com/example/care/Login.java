package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Login extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox guestCheck;
    private CheckBox businessCheck;
    private Button loginButton;

    private String email;
    private String password;



    static Activity thisActivity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUI();
        thisActivity = this;

        guestCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(guestCheck.isChecked()){
                    businessCheck.setChecked(false);
                }
                emailInput.setHint("Email");
            }
        });

        businessCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(businessCheck.isChecked()){
                    guestCheck.setChecked(false);
                }
                emailInput.setHint("Company Email");

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();
                if(!guestCheck.isChecked() & !businessCheck.isChecked()){
                    makeToast("Please select an account type");
                }
                else{
                    if(checkInputs()){
                        if(checkValidity()){
                            getData();
                        }
                    }
                }
            }
        });
    }

    // This method sets up the UI
    private void setUI(){
        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextPassword);
        guestCheck = findViewById(R.id.checkBoxGuest);
        businessCheck = findViewById(R.id.checkBoxBusiness);
        loginButton = findViewById(R.id.buttonLogin);
    }

    //Checks if all fields have been filled
    private boolean checkInputs(){
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        if(email.length() == 0 | password.length() == 0){
            makeToast("Please fill in all available fields");
            return false;
        }
        return true;
    }

    //Checks if inputs are valid inputs
    private boolean checkValidity(){
        if (!isEmailValid(email)) {
            makeToast("Email is invalid");
            return false;
        }
        if (password.length() < 6 | password.length() > 12) {
            makeToast("Password must be between 6 - 12 characters");
            return false;
        }
        return true;
    }

    //Checks if an account under the provided email exists, if so it checks the password
    private void getData(){
        DocumentReference docRef = db.collection("Users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Document exists!");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(guestCheck.isChecked()){
                            Guest g = new Guest((String)document.getData().get("email"), (String)document.getData().get("password"), (Boolean)document.getData().get("asGuest"));
                            checkLogin(g.getPassword());
                        }
                        else{
                            makeToast("Business Class has not been created yet");
                        }

                    } else {
                        makeToast("Incorrect Email");
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Error! Please Try Again.",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });
    }

    //Checks if the provided password is the password for the account
    private void checkLogin(String pass){
        if(password.equals(pass)){
            Toast.makeText(getApplicationContext(),"You Have Successfully Logged In!",Toast.LENGTH_SHORT).show();
            accountHomeActivity();
        }
        else{
            Toast.makeText(getApplicationContext(),"Incorrect Password.",Toast.LENGTH_SHORT).show();
        }
    }

    //Checks if the provided email is a valid email
    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }

    private void accountHomeActivity(){
        Intent intent = new Intent(this, AccountHome.class);
        startActivity(intent);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}