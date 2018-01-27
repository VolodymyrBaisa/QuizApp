package com.volodymyrbaisa.quizapp.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.volodymyrbaisa.quizapp.utils.PreconditionsUtils.checkNotNull;

/**
 * Created by Bios on 1/22/2018.
 */

public class ActivityUtils {
    private ActivityUtils() {throw new IllegalAccessError("Utility class");}

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void launchActivity(@NonNull Context context,
                                      @NonNull Class aClass) {
        Intent intent = new Intent(context, aClass);
        context.startActivity(intent);
    }
}
