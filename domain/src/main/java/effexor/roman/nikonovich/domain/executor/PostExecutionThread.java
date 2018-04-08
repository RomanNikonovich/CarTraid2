package effexor.roman.nikonovich.domain.executor;


import io.reactivex.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
