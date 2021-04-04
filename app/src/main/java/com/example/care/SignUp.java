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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";

    EditText emailInput;
    EditText passwordInput;
    EditText confirmPasswordInput;
    EditText nameInput;
    EditText phoneInput;
    CheckBox guestCheck;
    CheckBox businessCheck;
    Button signUpButton;

    String email;
    String password;
    String confirmPass;
    String name;
    String phoneNumb;

    private UserModel userModel;


    static Activity thisActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setUI();
        thisActivity = this;
        userModel = new UserModel(this);

        guestCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(guestCheck.isChecked()){
                    businessCheck.setChecked(false);
                }
                emailInput.setHint("Email");
                nameInput.setVisibility(View.VISIBLE);
                nameInput.setHint("Name");
            }
        });

        businessCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(businessCheck.isChecked()){
                    guestCheck.setChecked(false);
                }
                emailInput.setHint("Company Email");
                nameInput.setVisibility(View.VISIBLE);
                nameInput.setHint("Company Name");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSignUp:
                hideKeyboard(v);
                email = emailInput.getText().toString().trim();
                password = passwordInput.getText().toString().trim();
                confirmPass = confirmPasswordInput.getText().toString().trim();
                name = nameInput.getText().toString().trim();
                phoneNumb = phoneInput.getText().toString().trim();
                if(!guestCheck.isChecked() & !businessCheck.isChecked()){
                    userModel.makeToast("Please select an account type");
                }
                else{
                    if(checkInputs()){
                        if(userModel.signUp(email, password, confirmPass, phoneNumb)){
                            emailAvailable();
                        }
                    }
                }
                break;
        }
    }

    // This method sets up the UI
    private void setUI(){
        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextPassword);
        confirmPasswordInput = findViewById(R.id.editTextConfirmPassword);
        nameInput = findViewById(R.id.editTextName);
        phoneInput = findViewById(R.id.editTextPhoneNumb);
        guestCheck = findViewById(R.id.checkBoxGuest);
        businessCheck = findViewById(R.id.checkBoxBusiness);
        signUpButton = findViewById(R.id.buttonSignUp);
        signUpButton.setOnClickListener(this);
    }

    // This method collects all inputs to it's corresponding variables and checks if they all have been filled in
    private boolean checkInputs(){
        if(email.length() == 0 | password.length() == 0 | name.length() == 0 | phoneNumb.length() == 0){
            userModel.makeToast("Please fill in all available fields");
            return false;
        }
        return true;
    }

    //Saves the account to Firestore
    public void signUp(){
        saveToCloud();
        userModel.makeToast("Account Created!");
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
    }

    //Checks if an account with this email has already been created and if not, creates the account
    private void emailAvailable(){
        DocumentReference docRef = db.collection("Users").document(email);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Toast.makeText(getApplicationContext(),"Account with this email already exists",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Document exists!");
                    } else {
                        signUp();
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Error! Please Try Again.",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });
    }

    //Saves the guest account to the firestore
    private void saveToCloud() {
        if (guestCheck.isChecked()) {
            GuestAccount g = new GuestAccount(email, password, name, phoneNumb);
            db.collection("Users").document(email)
                    .set(g)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        } else {
            BusinessAccount b = new BusinessAccount(email, password, name, phoneNumb);
            Business business = new Business(name);
            db.collection("Users").document(email)
                    .set(b)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
            db.collection("Businesses").document(name)
                    .set(business)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });
        }
    }

    //method to hide the keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}