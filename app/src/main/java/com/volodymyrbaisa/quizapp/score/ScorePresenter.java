package com.volodymyrbaisa.quizapp.score;

/**
 * Created by Bios on 1/27/2018.
 */

public class ScorePresenter implements ScoreContract.Presenter {
    private ScoreContract.View scoreView;

    public ScorePresenter(ScoreContract.View scoreView) {
        this.scoreView = scoreView;
        scoreView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void showTotalScoreWithAnimation(int score){
        scoreView.showTotalScore(String.valueOf(score));
    }

    @Override
    public void restartQuiz() {
        scoreView.launchActivity();
    }
}
