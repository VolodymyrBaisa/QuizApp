package com.volodymyrbaisa.quizapp.launcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.quiz.QuizActivity;
import com.volodymyrbaisa.quizapp.utils.ActivityUtils;
import com.volodymyrbaisa.quizapp.utils.PreconditionsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bios on 1/22/2018.
 */

public class LauncherView extends RelativeLayout implements LauncherContract.View {
    private LauncherContract.Presenter presenter;

    public LauncherView(Context context) {
        super(context);
        init();
    }

    public LauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.launcher_view_content, this);
        ButterKnife.bind(this, view);
    }

    @Override
    public void setPresenter(LauncherContract.Presenter presenter) {
        this.presenter = PreconditionsUtils.checkNotNull(presenter);
    }

    @Override
    public void launchActivity() {
        ActivityUtils.launchActivity(getContext(), QuizActivity.class);
    }

    @OnClick(R.id.start_quiz_button)
    public void onClickStartQuiz(View view) {
        presenter.startQuiz();
    }
}
