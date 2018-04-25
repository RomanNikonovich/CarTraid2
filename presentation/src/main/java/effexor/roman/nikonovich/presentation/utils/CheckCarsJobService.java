package effexor.roman.nikonovich.presentation.utils;

        import android.app.job.JobParameters;
        import android.app.job.JobService;


public class CheckCarsJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        CheckDataIntentService.startCheckDataIntentService(getApplicationContext());
    /*    jobFinished(jobParameters, true);*/
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }

}
