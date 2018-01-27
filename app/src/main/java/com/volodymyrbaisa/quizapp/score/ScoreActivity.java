package com.volodymyrbaisa.quizapp.score;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.quiz.QuizFragment;
import com.volodymyrbaisa.quizapp.utils.ActivityUtils;

import static com.volodymyrbaisa.quizapp.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 1/27/2018.
 */

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        int totalQuizPoints = checkNotNull(bundle).getInt(QuizFragment.QUIZ_POINTS_KEY);

        ScoreFragment scoreFragment = (ScoreFragment) getSupportFragmentManager()
                .findFragmentById(R.id.scoreContentFrame);
        if (scoreFragment == null) {
            scoreFragment = ScoreFragment.newInstance(totalQuizPoints);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    scoreFragment, R.id.scoreContentFrame);
        }
        new ScorePresenter(scoreFragment);
    }
}
