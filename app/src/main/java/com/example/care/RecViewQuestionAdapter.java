package com.example.care;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewQuestionAdapter  extends RecyclerView.Adapter<RecViewQuestionAdapter.ViewHolder> {

    private ArrayList<Questions> questions = new ArrayList<>();
    private Context context;

    public RecViewQuestionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtQuestionMain.setText(questions.get(position).getQuestionMain());
        holder.txtQuestionDetail.setText(questions.get(position).getQuestionDetail());

        holder.chkBoxQuestionYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.chkBoxQuestionYes.setChecked(true);
                questions.get(position).setAnsType(AnswerType.YES);
                if (holder.chkBoxQuestionNo.isChecked()) {
                    holder.chkBoxQuestionNo.setChecked(false);
                }
            }
        });

        holder.chkBoxQuestionNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.chkBoxQuestionNo.setChecked(true);
                questions.get(position).setAnsType(AnswerType.NO);
                if (holder.chkBoxQuestionYes.isChecked()) {
                    holder.chkBoxQuestionYes.setChecked(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    // Method to get the list of questions
    public void setQuestions(ArrayList<Questions> questions) {

        this.questions = questions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout parent;
        private TextView txtQuestionMain;
        private TextView txtQuestionDetail;
        private CheckBox chkBoxQuestionYes;
        private CheckBox chkBoxQuestionNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize UI elements
            this.parent = itemView.findViewById(R.id.parentQuestion);
            this.txtQuestionMain = itemView.findViewById(R.id.txtQuestionMain);
            this.txtQuestionDetail = itemView.findViewById(R.id.txtQuestionDetail);
            this.chkBoxQuestionYes = itemView.findViewById(R.id.chkBoxQuestionYes);
            this.chkBoxQuestionNo = itemView.findViewById(R.id.chkBoxQuestionNo);

        }
    }
}
