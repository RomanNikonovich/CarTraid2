package effexor.roman.nikonovich.domain.iterators;


import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.domain.entity.entityChoose.MakeCar;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import io.reactivex.Flowable;

public class GetChooseUseCase extends BaseUseCase {
    private GetChooseRepository repository;

    @Inject
    public GetChooseUseCase(PostExecutionThread postExecutionThread, GetChooseRepository repository) {
        super(postExecutionThread);
        this.repository = repository;
    }


    public Flowable<List<MakeCar>> getChoose() {
        return repository
                .getChoose()
                .observeOn(postExecutionThread);
    }
}
