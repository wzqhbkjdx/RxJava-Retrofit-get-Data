package com.cgtrc.wzq.cgtcplatform.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.cgtrc.wzq.cgtcplatform.App;

/**
 * Created by bym on 16/3/23.
 */
public class UiUtils {

    private static Context context = App.context;


    public static void showSnack(View rootView, int textId) {
        Snackbar.make(rootView, context.getString(textId), Snackbar.LENGTH_SHORT).show();
    }
    public static void showSnackLong(View rootView, int textId) {
        Snackbar.make(rootView, context.getString(textId), Snackbar.LENGTH_LONG).show();
    }
}
