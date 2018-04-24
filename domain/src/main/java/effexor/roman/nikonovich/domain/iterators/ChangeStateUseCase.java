package effexor.roman.nikonovich.domain.iterators;

import javax.inject.Inject;

import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Completable;

public class ChangeStateUseCase extends BaseUseCase {

    private SearchVehicleRepository repository;

    @Inject
    public ChangeStateUseCase(PostExecutionThread postExecutionThread, SearchVehicleRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Completable changeState(String idSearch, String idCar) {
        return repository
                .changeState(idSearch, idCar)
                .observeOn(postExecutionThread);
    }

}
