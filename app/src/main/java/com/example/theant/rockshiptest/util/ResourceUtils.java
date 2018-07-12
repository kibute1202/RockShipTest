package com.example.theant.rockshiptest.util;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

public class ResourceUtils {

    public static int fetchColor(Context context, @ColorRes int color) {
        return ContextCompat.getColor(context, color);
    }

}
