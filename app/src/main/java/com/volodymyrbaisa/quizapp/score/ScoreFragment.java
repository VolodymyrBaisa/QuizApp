package com.volodymyrbaisa.quizapp.score;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.volodymyrbaisa.quizapp.launcher.LauncherContract;

import java.io.IOException;
import java.io.InputStream;

import static com.volodymyrbaisa.quizapp.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 1/22/2018.
 */

public class ScoreFragment extends Fragment implements ScoreContract.View{
    ScoreContract.Presenter presenter;

    public static ScoreFragment newInstance() {
        return new ScoreFragment();
    }

    @Override
    public void setPresenter(ScoreContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
