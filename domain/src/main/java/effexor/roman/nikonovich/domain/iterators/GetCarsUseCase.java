package effexor.roman.nikonovich.domain.iterators;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.domain.entity.vehicle.Vehicle;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class GetCarsUseCase extends BaseUseCase {
    private SearchVehicleRepository repository;

    @Inject
    public GetCarsUseCase(PostExecutionThread postExecutionThread, SearchVehicleRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Flowable<List<Vehicle>> getCars(String id){
        return repository
                .getCars(id)
                .doOnNext(new Consumer<List<Vehicle>>() {
                    @Override
                    public void accept(List<Vehicle> vehicles) throws Exception {
                        Collections.sort(vehicles);
                    }
                })
                .observeOn(postExecutionThread);
    }
}
