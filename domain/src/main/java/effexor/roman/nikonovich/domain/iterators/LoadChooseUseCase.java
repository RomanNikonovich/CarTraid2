package effexor.roman.nikonovich.domain.iterators;


import javax.inject.Inject;

import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import io.reactivex.Completable;

public class LoadChooseUseCase extends BaseUseCase {
    private GetChooseRepository repository;

    @Inject
    public LoadChooseUseCase(PostExecutionThread postExecutionThread, GetChooseRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Completable loadChoose() {
        return repository
                .loadChoose()
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
