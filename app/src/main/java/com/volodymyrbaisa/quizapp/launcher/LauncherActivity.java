package com.volodymyrbaisa.quizapp.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.volodymyrbaisa.quizapp.R;
import com.volodymyrbaisa.quizapp.utils.PreconditionsUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bios on 1/22/2018.
 */

public class LauncherActivity extends AppCompatActivity {
    @BindView(R.id.launcher_root_view) LauncherView launcherRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        LauncherContract.View launchView = launcherRootView;
        PreconditionsUtils.checkNotNull(launchView);
        new LauncherPresenter(launchView);
    }
}
