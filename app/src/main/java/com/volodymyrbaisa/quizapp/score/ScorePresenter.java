package com.volodymyrbaisa.quizapp.score;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Bios on 1/27/2018.
 */

public class ScorePresenter implements ScoreContract.Presenter {
    private ScoreContract.View scoreView;
    private CompositeDisposable compositeDisposable;

    public ScorePresenter(ScoreContract.View scoreView) {
        this.scoreView = scoreView;
        compositeDisposable = new CompositeDisposable();
        scoreView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
