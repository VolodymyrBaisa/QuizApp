package com.volodymyrbaisa.quizapp.quiz;

import com.google.gson.Gson;
import com.volodymyrbaisa.quizapp.model.QuizItems;
import com.volodymyrbaisa.quizapp.utils.ActivityUtils;
import com.volodymyrbaisa.quizapp.utils.IOUtils;

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
    private int displayPoints;
    private int points;
    private boolean enabledQuiz;

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

    @Override
    public void refillQuestionForm(){
        timer();
        showQuestionsProgress();
        showPoints();
        fillQuestionForm();
        enabledQuizButtons(enabledQuiz);
        if(!enabledQuiz) mainView.showRightAnswer();
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
                        onNext -> showTimer(onNext),
                        onError -> {},
                        () -> {
                            setPoints();
                            showPoints();
                            startNextQuizIfHas();
                        },
                        onSubscribe -> {
                            showQuestionsProgress();
                            fillQuestionForm();
                            mainView.resetQuizButtons();
                            enabledQuizButtons(true);
                        });

        compositeDisposable.add(timerDisposable);
    }

    private void setPoints() {
        displayPoints = points;
    }

    private void nextQuiz() {
        Disposable timerDisposable = Observable.interval(1, 1, TimeUnit.SECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(2)
                .subscribe(onNext -> {},
                        onError -> {},
                        () -> {
                            currentQuestion++;
                            startQuiz();
                        },
                        onSubscribe -> {});

        compositeDisposable.add(timerDisposable);
    }

    @Override
    public boolean checkRightAnswer(String ans){
       return quizItems[currentQuestion].getAnswer().equals(ans);
    }

    public void increasePointsIfRightAnswer(String ans){
        if (checkRightAnswer(ans)) points++;
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

    private void startNextQuizIfHas(){
        if (currentQuestion + 1 < totalQuestions){
            nextQuiz();
        } else {
            enabledQuizButtons(false);
        }
    }

    @Override
    public void enabledQuizButtons(boolean enabled) {
        enabledQuiz = enabled;
        mainView.showQuizButtonsIsEnabled(enabled);
    }

    private void setTotalQuestions() {
        totalQuestions = quizItems.length;
    }

    private void showQuestionsProgress() {
        mainView.setQuestionsProgress(currentQuestion + 1, totalQuestions);
    }

    private void showPoints() {
        mainView.setPoints(displayPoints);
    }

    private void showTimer(Long onNext) {
        mainView.setProgressTimer(onNext.intValue());
        mainView.setTimerAnalog(onNext.intValue());
    }

    private void fillQuestionForm() {
        mainView.setDescription(quizItems[currentQuestion].getDescription());
        mainView.setVersion1(quizItems[currentQuestion].getVersion1());
        mainView.setVersion2(quizItems[currentQuestion].getVersion2());
        mainView.setVersion3(quizItems[currentQuestion].getVersion3());
        mainView.setVersion4(quizItems[currentQuestion].getVersion4());
    }
}
