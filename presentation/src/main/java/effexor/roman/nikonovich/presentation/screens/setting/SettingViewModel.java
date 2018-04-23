package effexor.roman.nikonovich.presentation.screens.setting;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import effexor.roman.nikonovich.app.App;
import effexor.roman.nikonovich.data.sharedPref.AppSharedPrefs;
import effexor.roman.nikonovich.presentation.base.BaseViewModel;
import effexor.roman.nikonovich.presentation.utils.CheckCarsJobService;

public class SettingViewModel extends BaseViewModel {
    public final ObservableBoolean setNotif = new ObservableBoolean(true);
    public final ObservableBoolean setSound = new ObservableBoolean(true);
    public final ObservableLong millisec = new ObservableLong();
    public final ObservableInt position = new ObservableInt();
    private static final String NOTIF = "notif";
    private static final String SOUND = "sound";
    private JobScheduler jobScheduler;
    private static final int ID_SCHEDULE = 781598596;

    @Inject
    public AppSharedPrefs sharedPrefs;

    public SettingViewModel() {
        App.getAppComponent().inject(this);
        init();
    }

    private void init() {
        setNotif.set(sharedPrefs.getBool(NOTIF));
        setSound.set(sharedPrefs.getBool(SOUND));
    }

    public void saveSetting() {

        Toast.makeText(router.getActivity(), String.valueOf(millisec.get()),
                Toast.LENGTH_LONG).show();
        saveScheduler();
        sharedPrefs.setPosition(position.get());
        sharedPrefs.setBoolean(NOTIF, setNotif.get());
        sharedPrefs.setBoolean(SOUND, setSound.get());
        router.back();
    }

    private void saveScheduler() {
        jobScheduler =
                (JobScheduler) router.getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (millisec.get() != 0) {
            ComponentName jobService = new ComponentName(router.getActivity(), CheckCarsJobService.class);

            JobInfo.Builder builder = new JobInfo
                    .Builder(ID_SCHEDULE, jobService);
            builder
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setRequiresDeviceIdle(false)
                    .setPersisted(true)
                    .setRequiresCharging(false)
                    .setBackoffCriteria(TimeUnit.SECONDS.toMillis(10),
                            JobInfo.BACKOFF_POLICY_LINEAR)
                    .setPeriodic(millisec.get());
            jobScheduler.schedule(builder.build());
        } else {
            jobScheduler.cancel(ID_SCHEDULE);
        }
    }
}
