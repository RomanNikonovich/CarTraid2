package effexor.roman.nikonovich.domain.iterators;

import java.util.List;

import effexor.roman.nikonovich.domain.entity.vehicle.Car;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Flowable;

public class GetCarsUseCase extends BaseUseCase {
    private SearchVehicleRepository repository;

    public GetCarsUseCase(PostExecutionThread postExecutionThread, SearchVehicleRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Flowable<List<Car>> getCars(String id){
        return repository
                .getCars(id)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
