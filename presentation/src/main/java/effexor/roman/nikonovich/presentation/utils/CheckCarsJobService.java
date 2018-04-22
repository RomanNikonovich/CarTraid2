package effexor.roman.nikonovich.presentation.utils;

import android.app.job.JobParameters;
import android.app.job.JobService;

import effexor.roman.nikonovich.data.utils.CheckDataIntentService;

public class CheckCarsJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        CheckDataIntentService.startCheckDataIntentService(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
