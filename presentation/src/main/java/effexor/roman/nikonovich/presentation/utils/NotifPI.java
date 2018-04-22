package effexor.roman.nikonovich.presentation.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import effexor.roman.nikonovich.presentation.screens.mainScreen.MainActivity;

public class NotifPI {

    public static PendingIntent getPI(Context context) {
        return PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
