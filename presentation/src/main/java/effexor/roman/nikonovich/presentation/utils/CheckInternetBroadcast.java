/*
package effexor.roman.nikonovich.presentation.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import effexor.roman.nikonovich.data.utils.CheckDataIntentService;

import static android.content.Context.MODE_PRIVATE;

public class CheckInternetBroadcast extends BroadcastReceiver {
    private static final String UNIQ_ACTION = "effexor.roman.nikonovich";
    private final String TABLE_NAME = "first run";
    private final String SCHEDULE = "schedule";
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        if (checkInternet()) {
            int period = getSchedule();
            */
/*if (period != 0) {*//*

                Intent intentP = new Intent(context, CheckDataIntentService.class);
                intentP.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intentP.setAction(UNIQ_ACTION);
                PendingIntent pendingIntent = PendingIntent.getService(context, 0, intentP,
                        PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                        //FIXME make 15 sec
                        System.currentTimeMillis() + 5000,
                        */
/*period*//*
60000,
                        pendingIntent);
            */
/*}*//*

        }
        this.context = null;
    }

    private int getSchedule() {
        SharedPreferences pref = context.getSharedPreferences(TABLE_NAME, MODE_PRIVATE);
        return pref.getInt(SCHEDULE, 0);
    }

    boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
*/
