package com.volodymyrbaisa.quizapp.launcher;

/**
 * Created by Bios on 1/22/2018.
 */

public class LauncherPresenter implements LauncherContract.Presenter {

    private LauncherContract.View launcherView;

    public LauncherPresenter(LauncherContract.View launcherView) {
        this.launcherView = launcherView;
        launcherView.setPresenter(this);
    }

    @Override
    public void startQuiz() {
        launcherView.launchActivity();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
