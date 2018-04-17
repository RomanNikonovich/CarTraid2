package effexor.roman.nikonovich.domain.iterators;

import javax.inject.Inject;

import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Completable;

public class AddSearchUseCase extends BaseUseCase {
    private SearchVehicleRepository repository;

    @Inject
    public AddSearchUseCase(PostExecutionThread postExecutionThread, SearchVehicleRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Completable addSearch(String url, String nameSearch){
        return repository
                .addSearch(url, nameSearch)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
