package com.volodymyrbaisa.quizapp.quiz;

import com.volodymyrbaisa.quizapp.base.BasePresenter;
import com.volodymyrbaisa.quizapp.base.BaseView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Bios on 1/22/2018.
 */

public interface QuizContract {
    interface View extends BaseView<QuizContract.Presenter> {
        InputStream getQuizData() throws IOException;
        void configureProgressTimer(int max, int progress);
        void setProgressTimer(int time);
        void setTimerAnalog(int time);
        void setQuestionsProgress(int currQuest, int totalQuest);
        void setPoints(int points);
        void setDescription(String descript);
        void setVersion1(String v1);
        void setVersion2(String v2);
        void setVersion3(String v3);
        void setVersion4(String v4);
        void resetQuizButtons();
        void enabledQuizButtons(boolean enabled);
    }

    interface Presenter extends BasePresenter {
        boolean checkRightAnswer(String ans);
        void increasePoints(String ans);
    }
}
