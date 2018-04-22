/*
package effexor.roman.nikonovich.data.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import effexor.roman.nikonovich.data.sharedPref.AppSharedPrefs;

public class InitAlarmManager {
    private static final String UNIQ_ACTION = "effexor.roman.nikonovich";
    private Context context;
    private AppSharedPrefs sharedPrefs;

    @Inject
    public InitAlarmManager(Context context, AppSharedPrefs sharedPrefs) {
        this.context = context;
        this.sharedPrefs = sharedPrefs;
    }

    public void init() {
        Intent intent = new Intent(context, CheckDataIntentService.class);
        intent.setAction(UNIQ_ACTION);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + sharedPrefs.getPosition(),
                sharedPrefs.getPosition(),
                pendingIntent);
    }
}
*/
