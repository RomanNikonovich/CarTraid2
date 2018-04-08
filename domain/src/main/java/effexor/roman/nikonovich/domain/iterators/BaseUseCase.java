package effexor.roman.nikonovich.domain.iterators;


import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase {
    protected Scheduler postExecutionThread;

    protected Scheduler threadExecution;

    public BaseUseCase(PostExecutionThread postExecutionThread) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        this.threadExecution = Schedulers.io();
    }

    public BaseUseCase(PostExecutionThread postExecutionThread, Scheduler threadExecution) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        this.threadExecution = threadExecution;
    }


}
