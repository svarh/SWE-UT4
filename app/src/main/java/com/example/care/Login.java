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


public class Login extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "MainActivity";
    private static Activity thisActivity = null;

    private EditText emailInput;
    private EditText passwordInput;
    private CheckBox guestCheck;
    private CheckBox businessCheck;
    private Button logInButton;

    private String email;
    private String password;
    private boolean isGuest;

    private UserModel userModel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonLogIn:
                hideKeyboard(v);
                this.email = emailInput.getText().toString();
                this.password = passwordInput.getText().toString();
                this.userModel.login(this.email, this.password);
                if(userModel.login(email, password)){
                    getData();
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.thisActivity = this;

        initialize();
    }

    // This method initialize properties and UI
    private void initialize(){

        // Initialize properties
        this.email = "";
        this.password = "";
        this.isGuest = true;
        this.userModel = new UserModel(this.thisActivity);

        // Initialize UI elements
        this.emailInput = findViewById(R.id.editTextEmail);
        this.passwordInput = findViewById(R.id.editTextPassword);
        this.guestCheck = findViewById(R.id.checkBoxGuest);
        this.businessCheck = findViewById(R.id.checkBoxBusiness);
        this.logInButton = findViewById(R.id.buttonLogIn);
        this.logInButton.setOnClickListener(this);

        // Initialize Check Boxes
        this.guestCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.guestCheck.isChecked()){
                this.businessCheck.setChecked(false);
            }
            this.emailInput.setHint("Email");
        });

        this.businessCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(this.businessCheck.isChecked()){
                this.guestCheck.setChecked(false);
            }
            this.emailInput.setHint("Company Email");

        });
    }

    public boolean checkInputs(){
        if (email.length() == 0 | password.length() == 0){
            userModel.makeToast("Please fill in all available fields");
            return false;
        }
        return true;
    }

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
                        if(guestCheck.isChecked()) {
                            Guest g = new Guest((String) document.getData().get("email"), (String) document.getData().get("password"), (String) document.getData().get("name"), (String) document.getData().get("phone"));
                            if (g.getPassword().equals(password)) {
                                userModel.makeToast("Login Successful!");
                                //Create intents to save guest object and move to accountHome
                            } else {
                                userModel.makeToast("Incorrect password. Please try again!");
                            }
                        }
                        else if(businessCheck.isChecked()){
                            Business b = new Business((String)document.getData().get("companyEmail"), (String)document.getData().get("password"), (String)document.getData().get("companyName"), (String)document.getData().get("phone"));
                            if(b.getPassword().equals(password)){
                                userModel.makeToast("Login Successful!");
                                //Create intents to save business object and move to accountHome
                            }
                            else{
                                userModel.makeToast("Incorrect password. Please try again!");
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Incorrect email. Please try again!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Document does not exist!");
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Error! Please Try Again.",Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed with: ", task.getException());
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}