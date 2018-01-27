package com.volodymyrbaisa.quizapp.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.custom.QuizRadioGroup;
import com.volodymyrbaisa.quizapp.score.ScoreActivity;
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

public class QuizFragment extends Fragment implements QuizContract.View {
    public static final String QUIZ_FILE = "quiz.json";
    public static final String QUIZ_POINTS_KEY = "quiz_points";

    @BindView(R.id.quiz_timer_progress) ProgressBar timerProgress;
    @BindView(R.id.quiz_timer_analog) TextView timerAnalog;
    @BindView(R.id.quiz_questions_progress) TextView questionsProgress;
    @BindView(R.id.quiz_points_progress) TextView pointsProgress;
    @BindView(R.id.quiz_question_description) TextView questionDescription;
    @BindView(R.id.quiz_radio_group) QuizRadioGroup radioGroup;
    @BindView(R.id.quiz_answer_1) RadioButton rButtonAnswer1;
    @BindView(R.id.quiz_answer_2) RadioButton rButtonAnswer2;
    @BindView(R.id.quiz_answer_3) RadioButton rButtonAnswer3;
    @BindView(R.id.quiz_answer_4) RadioButton rButtonAnswer4;

    private QuizContract.Presenter presenter;

    public static QuizFragment newInstance() {
        return new QuizFragment();
    }

    @Override
    public void setPresenter(QuizContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.quiz_fragment, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState == null) {
            presenter.subscribe();
        } else {
            presenter.refillQuestionForm();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    public InputStream getQuizData() throws IOException {
        return getContext().getAssets().open(QUIZ_FILE);
    }

    @Override
    public void configureProgressTimer(int max, int progress) {
        timerProgress.setMax(max);
        timerProgress.setProgress(progress);
    }

    @Override
    public void setProgressTimer(int time) {
        timerProgress.setProgress(time);
    }

    @Override
    public void setTimerAnalog(int time) {
        timerAnalog.setText(String.valueOf(time));
    }

    @Override
    public void setQuestionsProgress(int currQuest, int totalQuest) {
        questionsProgress.setText(String.valueOf(currQuest + "\\" + totalQuest));
    }

    @Override
    public void setPoints(int points) {
        pointsProgress.setText(String.valueOf(points));
    }

    @Override
    public void setDescription(String descript) {
        questionDescription.setText(descript);
    }

    @Override
    public void setVersion1(String v1) {
        rButtonAnswer1.setText(v1);
    }

    @Override
    public void setVersion2(String v2) {
        rButtonAnswer2.setText(v2);
    }

    @Override
    public void setVersion3(String v3) {
        rButtonAnswer3.setText(v3);
    }

    @Override
    public void setVersion4(String v4) {
        rButtonAnswer4.setText(v4);
    }

    @Override
    public void resetQuizButtons(){
        radioGroup.clearCheck();
        radioGroup.setQuizButtonsColor(R.drawable.round_button_pink);
    }

    @Override
    public void showQuizButtonsIsEnabled(boolean enabled){
        radioGroup.setQuizButtonsEnabled(enabled);
    }

    @OnClick({R.id.quiz_answer_1, R.id.quiz_answer_2, R.id.quiz_answer_3, R.id.quiz_answer_4})
    public void onClickAnswer(View view){
        presenter.enabledQuizButtons(false);
        showRightAnswer();
        presenter.increasePointsIfRightAnswer(((RadioButton)view).getText().toString());
    }

    @Override
    public void showRightAnswer() {
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton answerButton = (RadioButton) radioGroup.getChildAt(i);
            if (presenter.checkRightAnswer(answerButton.getText().toString())) {
                answerButton.setBackgroundResource(R.drawable.round_button_green);
            } else {
                answerButton.setBackgroundResource(R.drawable.round_button_red);
            }
        }
    }

    public void launchScoreActivity(int points){
        Bundle bundle = new Bundle();
        bundle.putInt(QUIZ_POINTS_KEY, points);
        ActivityUtils.launchActivity(getContext(), ScoreActivity.class, bundle);
    }
}
