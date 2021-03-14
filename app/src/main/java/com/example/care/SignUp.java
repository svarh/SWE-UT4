package com.example.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class SignUp extends AppCompatActivity {
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
    String name;
    String phoneNumb;


    static Activity thisActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setUI();
        thisActivity = this;

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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(v);
                if(!guestCheck.isChecked() & !businessCheck.isChecked()){
                    makeToast("Please select an account type");
                }
                else if(guestCheck.isChecked()){
                    if(checkInputs("Guest")){
                        if(checkValidity(password, email, phoneNumb)) {
                            emailAvailable();
                        }
                    }
                }
                else if(businessCheck.isChecked()){
                    if(checkInputs("Business")){
                        if(checkValidity(password, email, phoneNumb)) {
                            emailAvailable();
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
        confirmPasswordInput = findViewById(R.id.editTextConfirmPassword);
        nameInput = findViewById(R.id.editTextName);
        phoneInput = findViewById(R.id.editTextPhoneNumb);
        guestCheck = findViewById(R.id.checkBoxGuest);
        businessCheck = findViewById(R.id.checkBoxBusiness);
        signUpButton = findViewById(R.id.buttonSignUp);
    }

    // This method collects all inputs to it's corresponding variables and checks if they all have been filled in
    private boolean checkInputs(String type){
        email = emailInput.getText().toString();
        password = passwordInput.getText().toString();
        name = nameInput.getText().toString();
        phoneNumb = phoneInput.getText().toString();
        if(email.length() == 0 | password.length() == 0 | name.length() == 0 | phoneNumb.length() == 0){
            makeToast("Please fill in all available fields");
            return false;
        }
        return true;
    }

    // This method checks if the email input is a valid email
    private boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    // This method checks if the inputs for password, email, and phone number are valid
    private boolean checkValidity(String password, String email, String phoneNumb){
        if (password.length() < 6 | password.length() > 12) {
            makeToast("Password must be between 6 - 12 characters");
            return false;
        } else if (!password.equals(confirmPasswordInput.getText().toString())) {
            makeToast("Passwords do not match");
            return false;
        }
        if (!isEmailValid(email)) {
            makeToast("Email is invalid");
            return false;
        }
        if (phoneNumb.length() != 10) {
            makeToast("Phone number is invalid");
            return false;
        }
        return true;
    }

    //Saves the account to Firestore
    public void signUp(){
        saveToCloud();
        makeToast("Account Created!");
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
            Guest g = new Guest(email, password, true, name, phoneNumb);
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
            makeToast("Business class does not exist");
        }
    }

    // This method creates Toast to send notification
    public static void makeToast(String str) {
        Toast.makeText(thisActivity, str, Toast.LENGTH_SHORT).show();
    }

    //method to hide the keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}