package effexor.roman.nikonovich.domain.iterators;

import java.util.List;

import effexor.roman.nikonovich.domain.entity.vehicle.Search;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.SearchVehicleRepository;
import io.reactivex.Flowable;

public class GetSearchListUseCase extends BaseUseCase{
    private SearchVehicleRepository repository;


    public GetSearchListUseCase(PostExecutionThread postExecutionThread, SearchVehicleRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }

    public Flowable<List<Search>> searchList(){
        return repository
                .getSearchList()
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
