package effexor.roman.nikonovich.data.repostitory;


import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.data.entity.entityChooseNet.MakeCarNet;
import effexor.roman.nikonovich.data.entity.entityChooseRealm.MakeCarRealm;
import effexor.roman.nikonovich.data.restApi.RestService;
import effexor.roman.nikonovich.domain.entity.entityChoose.ChooseCars;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.realm.Realm;

public class GetChooseRepositoryImpl implements GetChooseRepository {
    private RestService restService;
    private Realm realm;

    @Inject
    public GetChooseRepositoryImpl(RestService restService, Realm realm) {
        this.restService = restService;
        this.realm = realm;
    }

    @Override
    public Completable loadChoose() {

        return restService
                .loadMakes()
                .map(new Function<List<MakeCarNet>, List<MakeCarRealm>>() {

                    @Override
                    public List<MakeCarRealm> apply(List<MakeCarNet> makeCarNets) throws Exception {
                        return ConvertToRealm.convertList(makeCarNets);
                    }
                })
                .doOnNext(new Consumer<List<MakeCarRealm>>() {
                    @Override
                    public void accept(List<MakeCarRealm> list) throws Exception {
                        realm.beginTransaction();
                        realm.insert(list);
                        realm.commitTransaction();

                    }
                })
                .ignoreElements();

    }

    @Override
    public Observable<ChooseCars> getChoose() {
        return null;
    }
}
