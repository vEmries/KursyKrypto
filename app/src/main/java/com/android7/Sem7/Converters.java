package com.android7.Sem7;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class Converters {

    public static String timestampToString (Integer timestamp) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(timestamp * 1000L);

        String date = DateFormat.format("dd", calendar).toString();

        return date;
    }
}
