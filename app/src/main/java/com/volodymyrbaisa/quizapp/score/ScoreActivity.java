package com.volodymyrbaisa.quizapp.score;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.utils.ActivityUtils;

/**
 * Created by Bios on 1/27/2018.
 */

public class ScoreActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ScoreFragment scoreFragment = (ScoreFragment) getSupportFragmentManager()
                .findFragmentById(R.id.quizContentFrame);
        if (scoreFragment == null) {
            scoreFragment = ScoreFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    scoreFragment, R.id.quizContentFrame);
        }

        new ScorePresenter(scoreFragment);
    }
}
