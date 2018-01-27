package com.volodymyrbaisa.quizapp.custom;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.RadioGroup;

/**
 * Created by Bios on 1/26/2018.
 */

public class QuizRadioGroup extends RadioGroup {
    public QuizRadioGroup(Context context) {
        super(context);
    }

    public QuizRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setQuizButtonsEnabled(boolean enabled) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(enabled);
        }
    }

    public void setQuizButtonsColor(@DrawableRes int id) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setBackgroundResource(id);

        }
    }
}
