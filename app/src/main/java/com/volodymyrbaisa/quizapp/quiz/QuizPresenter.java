package com.volodymyrbaisa.quizapp.quiz;

import com.google.gson.Gson;
import com.volodymyrbaisa.quizapp.model.QuizItems;
import com.volodymyrbaisa.quizapp.utils.IOUtils;
import com.volodymyrbaisa.quizapp.utils.PreconditionsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.volodymyrbaisa.quizapp.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 1/22/2018.
 */

public class QuizPresenter implements QuizContract.Presenter {
    private static final int TIMER = 15;
    private QuizContract.View mainView;
    private CompositeDisposable compositeDisposable;

    private QuizItems[] quizItems;
    private int currentQuestion;
    private int totalQuestions;
    private int points;

    public QuizPresenter(QuizContract.View quizView) {
        this.mainView = quizView;
        compositeDisposable = new CompositeDisposable();
        quizView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        readQuizFile();
        setTotalQuestions();
        timer();
        startQuiz();

    }

    private void timer() {
        mainView.configureProgressTimer(TIMER, TIMER);
        mainView.setTimerAnalog(TIMER);
    }


    private void startQuiz() {
        Disposable timerDisposable = Observable.interval(1, 1, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(TIMER + 1)
                .map(v -> TIMER - v)
                .subscribe(
                        onNext -> setTimer(onNext),
                        onError -> {},
                        () -> {
                            mainView.setPoints(points);
                            startNextQuizIfHas();
                        },
                        onSubscribe -> {
                            mainView.setQuestionsProgress(currentQuestion + 1, totalQuestions);
                            fillQuestionForm(currentQuestion);
                        });

        compositeDisposable.add(timerDisposable);
    }

    private void setTimer(Long onNext) {
        mainView.setProgressTimer(onNext.intValue());
        mainView.setTimerAnalog(onNext.intValue());
    }

    private void startNextQuizIfHas(){
        if (currentQuestion + 1 < totalQuestions){
            nextQuiz();
        } else {
            mainView.enabledQuizButtons(false);
        }
    }

    private void nextQuiz() {
        Disposable timerDisposable = Observable.interval(1, 1, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(2)
                .subscribe(onNext -> {},
                        onError -> {},
                        () -> {
                            currentQuestion++;
                            mainView.resetQuizButtons();
                            mainView.enabledQuizButtons(true);
                            startQuiz();
                        },
                        onSubscribe -> {});

        compositeDisposable.add(timerDisposable);
    }

    @Override
    public boolean checkRightAnswer(String ans){
       return quizItems[currentQuestion].getAnswer().equals(ans);
    }

    public void increasePoints(String ans){
        if (checkRightAnswer(ans)) points++;
    }

    private void fillQuestionForm(int index) {
        mainView.setDescription(quizItems[index].getDescription());
        mainView.setVersion1(quizItems[index].getVersion1());
        mainView.setVersion2(quizItems[index].getVersion2());
        mainView.setVersion3(quizItems[index].getVersion3());
        mainView.setVersion4(quizItems[index].getVersion4());
    }

    @Override
    public void unsubscribe() {
        checkNotNull(compositeDisposable).dispose();
    }

    private void readQuizFile() {
        try {
            quizItems = new Gson().fromJson(IOUtils.toString(mainView.getQuizData()), QuizItems[].class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load " + QuizFragment.QUIZ_FILE, e);
        }
    }

    private void setTotalQuestions() {
        totalQuestions = quizItems.length;
    }
}
