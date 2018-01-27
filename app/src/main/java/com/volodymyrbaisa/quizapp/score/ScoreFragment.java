package com.volodymyrbaisa.quizapp.score;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.launcher.LauncherContract;
import com.volodymyrbaisa.quizapp.quiz.QuizActivity;
import com.volodymyrbaisa.quizapp.utils.ActivityUtils;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.volodymyrbaisa.quizapp.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 1/22/2018.
 */

public class ScoreFragment extends Fragment implements ScoreContract.View{
    private static final String TOTAL_QUIZ_POINTS_KEY = "total_quiz_points";

    ScoreContract.Presenter presenter;
    private int totalQuizPoints;

    @BindView(R.id.total_score) TextView totalScore;

    public static ScoreFragment newInstance(int totalPoints) {
        Bundle bundle = new Bundle();
        bundle.putInt(TOTAL_QUIZ_POINTS_KEY, totalPoints);
        ScoreFragment scoreFragment = new ScoreFragment();
        scoreFragment.setArguments(bundle);
        return scoreFragment;
    }

    @Override
    public void setPresenter(ScoreContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.score_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if(savedInstanceState == null) {
            totalQuizPoints = checkNotNull(bundle).getInt(TOTAL_QUIZ_POINTS_KEY, 0);
        } else {
            totalQuizPoints = savedInstanceState.getInt(TOTAL_QUIZ_POINTS_KEY);
        }
        presenter.showTotalScoreWithAnimation(totalQuizPoints);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TOTAL_QUIZ_POINTS_KEY, totalQuizPoints);
    }

    @OnClick(R.id.restart_quiz_button)
    public void onClickRestartQuizButton(View view){
        presenter.restartQuiz();
    }

    @Override
    public void showTotalScore(String score){
        totalScore.setText(score);
    }

    public void launchActivity() {
        ActivityUtils.launchActivity(getContext(), QuizActivity.class);
    }
}
