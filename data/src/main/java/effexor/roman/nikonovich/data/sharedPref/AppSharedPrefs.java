package effexor.roman.nikonovich.data.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

public class AppSharedPrefs {
    private final String TABLE_NAME = "setting_table";
    private final String FIRST_RUN_VAR = "first";
    private final String SHOW_TIPS_VAR = "show tips";
    private final String POSITION = "position";
    /*    private final String SCHEDULE = "schedule";*/
    private Context context;
    private SharedPreferences pref;

    @Inject
    public AppSharedPrefs(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(TABLE_NAME, MODE_PRIVATE);
    }

    public void saveTipsShow() {
        setVariable(SHOW_TIPS_VAR);
    }

    public boolean getTipsShow() {
        return getBool(SHOW_TIPS_VAR);
    }

    public boolean isFirstRun() {
        return getBool(FIRST_RUN_VAR);
    }

    public void saveFirstRun() {
        setVariable(FIRST_RUN_VAR);
    }

/*    public long getSchedule(){
        pref = context.getSharedPreferences(TABLE_NAME, MODE_PRIVATE);
        return pref.getLong(SCHEDULE, 0);
    }
    public void setSchedule(Long schedule){
        pref
                .edit()
                .putLong(SCHEDULE, schedule)
                .apply();
    }*/

    public int getPosition() {
        return pref.getInt(POSITION, 0);
    }

    public void setPosition(int position) {
        pref
                .edit()
                .putInt(POSITION, position)
                .apply();
    }

    public boolean getBool(String booleanName) {
        if (pref.getBoolean(booleanName, true)) {
            return true;
        } else {
            return false;
        }
    }

    public void setBoolean(String booleanName, Boolean bool) {
        pref
                .edit()
                .putBoolean(booleanName, bool)
                .apply();
    }

    private void setVariable(String booleanName) {
        pref
                .edit()
                .putBoolean(booleanName, false)
                .apply();
    }
}
