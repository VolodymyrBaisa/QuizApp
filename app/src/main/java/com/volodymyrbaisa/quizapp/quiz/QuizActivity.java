package com.volodymyrbaisa.quizapp.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.utils.ActivityUtils;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuizFragment quizFragment = (QuizFragment) getSupportFragmentManager()
                .findFragmentById(R.id.quizContentFrame);
        if (quizFragment == null) {
            quizFragment = QuizFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    quizFragment, R.id.quizContentFrame);

            new QuizPresenter(quizFragment);
        }
    }
}
