package com.volodymyrbaisa.quizapp.score;

import com.volodymyrbaisa.quizapp.base.BasePresenter;
import com.volodymyrbaisa.quizapp.base.BaseView;

/**
 * Created by Bios on 1/27/2018.
 */

public interface ScoreContract {
    interface View extends BaseView<ScoreContract.Presenter> {
        void launchActivity();
        void showTotalScore(String score);
        void showTotalScoreToast(int score);
    }

    interface Presenter extends BasePresenter {
        void restartQuiz();
        void showTotalScoreWithAnimation(int score);
    }
}
