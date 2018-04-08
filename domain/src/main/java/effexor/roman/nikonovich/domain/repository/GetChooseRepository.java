package effexor.roman.nikonovich.domain.repository;


import java.util.List;

import effexor.roman.nikonovich.domain.entity.entityChoose.MakeCar;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface GetChooseRepository {

    Completable loadChoose();

    Flowable<List<MakeCar>> getChoose();

}
