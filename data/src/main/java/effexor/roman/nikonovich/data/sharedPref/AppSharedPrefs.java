package effexor.roman.nikonovich.data.sharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

public class AppSharedPrefs {
    private final String TABLE_NAME = "first run";
    private final String FIRST_RUN_VAR = "first";
    private final String SHOW_TIPS_VAR = "show tips";
    private Context context;
    private SharedPreferences pref;

    @Inject
    public AppSharedPrefs(Context context) {
        this.context = context;
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

    private boolean getBool(String booleanName) {
        pref = context.getSharedPreferences(TABLE_NAME, MODE_PRIVATE);
        if (pref.getBoolean(booleanName, true)) {
            return true;
        } else {
            return false;
        }
    }

    private void setVariable(String booleanName) {
        pref
                .edit()
                .putBoolean(booleanName, false)
                .apply();
    }
}
