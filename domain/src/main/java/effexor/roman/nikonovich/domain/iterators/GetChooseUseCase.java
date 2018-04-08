package effexor.roman.nikonovich.domain.iterators;


import effexor.roman.nikonovich.domain.entity.entityChoose.ChooseCars;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import io.reactivex.Observable;

public class GetChooseUseCase extends BaseUseCase {
    private GetChooseRepository repository;

    public GetChooseUseCase(PostExecutionThread postExecutionThread, GetChooseRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Observable<ChooseCars> getChoose() {
        return repository
                .getChoose()
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
