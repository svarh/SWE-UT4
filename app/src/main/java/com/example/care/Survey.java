package com.example.care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.SecureRandom;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Survey extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    static Activity thisActivity = null;

    private RecyclerView recViewQuestion;
    private RecViewQuestionAdapter adapter;
    private Button btnSubmit;

    private int numberOfNo;
    private int numberOfYes;

    private GuestAccount guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        guest = getIntent().getExtras().getParcelable("User");

        initUI();

    }

    // Method to initialize UI elements
    private void initUI() {

        this.thisActivity = this;

        // Initialize RecyclerView
        adapter = new RecViewQuestionAdapter(this);
        recViewQuestion = findViewById(R.id.recViewQuestion);
        recViewQuestion.setAdapter(adapter);
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));

        // Get the list of questions
        ArrayList<Questions> questions = getQuestions();
        adapter.setQuestions(questions);

        // Initialize answer counts
        numberOfNo = 0;
        numberOfYes = 0;

        // Initialize button
        btnSubmit = findViewById(R.id.btnSubmitSurvey);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfNo = 0;
                numberOfYes = 0;
                for (Questions q : questions) {
                    if (q.getAnsType() == AnswerType.YES) {
                        numberOfYes++;
                    }
                    if (q.getAnsType() == AnswerType.NO) {
                        numberOfNo++;
                    }
                }
                Toast.makeText(thisActivity, "Yes: " + numberOfYes + " No: " + numberOfNo ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Survey.this, GuestHome.class);
                intent.putExtra("User", guest);
                startActivity(intent);
            }
        });
    }

    // Method to get the list of questions
    public ArrayList<Questions> getQuestions() {

        ArrayList<Questions> arr = new ArrayList<>();
        Questions q1 = new Questions(1,
                "Have you experienced any of the following symptoms in the past 48 hours:",
                "- fever or chills \n- cough \n- shortness of breath or difficulty breathing \n- fatigue \n- muscle or body aches \n- headache \n- new loss of taste or smell \n- sore throat \n- congestion or runny nose \n- nausea or vomiting \n- diarrhea"
        );
        Questions q2 = new Questions(2,
                "Have you been in close physical contact in the last 14 days with:",
                "- Anyone who is known to have laboratory-confirmed COVID-19? \n    OR \n- Anyone who has any symptoms consistent with COVID-19?"
        );
        Questions q3 = new Questions(3,
                "Are you isolating or quarantining because you may have been exposed to a person with COVID-19 or are worried that you may be sick with COVID-19?",
                ""
        );
        Questions q4 = new Questions(4,
                "Are you currently waiting on the results of a COVID-19 test?",
                ""
        );
        Questions q5 = new Questions(5,
                "Have you traveled in the past 10 days?",
                ""
        );
        Questions q6 = new Questions(6,
                "By hitting submit I certify that my responses are true and correct",
                ""
        );

        arr.add(q1);
        arr.add(q2);
        arr.add(q3);
        arr.add(q4);
        arr.add(q5);
        arr.add(q6);

        return arr;
    }
}