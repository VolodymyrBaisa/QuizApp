package com.volodymyrbaisa.quizapp.score;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.volodymyrbaisa.quizapp.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 1/27/2018.
 */

public class ScorePresenter implements ScoreContract.Presenter {
    private ScoreContract.View scoreView;

    private CompositeDisposable compositeDisposable;

    public ScorePresenter(ScoreContract.View scoreView) {
        this.scoreView = scoreView;
        scoreView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void unsubscribe() {
        checkNotNull(compositeDisposable).dispose();
    }

    @Override
    public void showTotalScoreWithAnimation(int score) {
        Disposable timerDisposable = Observable.interval(70, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(score + 1)
                .subscribe(
                        onNext -> scoreView.showTotalScore(String.valueOf(onNext.intValue())),
                        onError -> {},
                        () -> scoreView.showTotalScoreToast(score),
                        onSubscribe -> {});

        compositeDisposable.add(timerDisposable);
    }

    @Override
    public void restartQuiz() {
        scoreView.launchActivity();
    }
}
