package effexor.roman.nikonovich.domain.iterators;

import javax.inject.Inject;

import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Completable;

public class DeleteSearchUseCase extends BaseUseCase {
    private SearchVehicleRepository repository;

    @Inject
    public DeleteSearchUseCase(PostExecutionThread postExecutionThread, SearchVehicleRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Completable deleteSearch(String id){
        return repository
                .deleteSearch(id)
                .observeOn(postExecutionThread);
    }


}
