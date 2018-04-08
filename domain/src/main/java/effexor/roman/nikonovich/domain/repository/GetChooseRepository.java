package effexor.roman.nikonovich.domain.repository;


import effexor.roman.nikonovich.domain.entity.entityChoose.ChooseCars;
import io.reactivex.Completable;
import io.reactivex.Observable;

public interface GetChooseRepository {

    Completable loadChoose();

    Observable<ChooseCars> getChoose();

}
