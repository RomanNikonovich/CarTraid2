package effexor.roman.nikonovich.domain.iterators;


import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.domain.entity.choose.MakeCar;
import effexor.roman.nikonovich.domain.executor.PostExecutionThread;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

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
                .doOnNext(new Consumer<List<MakeCar>>() {
                    @Override
                    public void accept(List<MakeCar> makeCars) throws Exception {
                        for(MakeCar makeCar: makeCars){
                            Collections.sort(makeCar.getModelsCars());
                        }
                    }
                })
                .observeOn(postExecutionThread);
    }
}
