package com.example.care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Survey extends AppCompatActivity {

    private RecyclerView recViewQuestion;
    private RecViewQuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_2);

        adapter = new RecViewQuestionAdapter(this);
        recViewQuestion = findViewById(R.id.recViewQuestion);

        recViewQuestion.setAdapter(adapter);
        recViewQuestion.setLayoutManager(new LinearLayoutManager(this));


        ArrayList<Questions> questions = getQuestions();
        adapter.setQuestions(questions);
    }

    // Method to get the list of questions
    public ArrayList<Questions> getQuestions() {

        // TODO: Get the list of questions and delete the dummies
        ArrayList<Questions> arr = new ArrayList<>();
        Questions q1 = new Questions(1,
                "Have you experienced any of the following symptoms in the past 48 hours:",
                "- fever or chills \n- cough \n- shortness of breath or difficulty breathing \n- fatigue \n- muscle or body aches \n- headache \n- new loss of taste or smell \n- sore throat \n- congestion or runny nose \n- nausea or vomiting \n- diarrhe"
        );
        Questions q2 = new Questions(2,
                "Have you been in close physical contact in the last 14 days with:",
                "- Anyone who is known to have laboratory-confirmed COVID-19? \n    OR \n- Anyone who has any symptoms consistent with COVID-19?"
        );

        arr.add(q1);
        arr.add(q2);

        return arr;
    }
}