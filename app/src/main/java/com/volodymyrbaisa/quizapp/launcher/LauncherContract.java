package com.volodymyrbaisa.quizapp.launcher;

import com.volodymyrbaisa.quizapp.base.BasePresenter;
import com.volodymyrbaisa.quizapp.base.BaseView;

/**
 * Created by Bios on 1/22/2018.
 */

public interface LauncherContract {
    interface View extends BaseView<Presenter> {
        void launchActivity();
    }

    interface Presenter extends BasePresenter {
        void startQuiz();
    }
}
