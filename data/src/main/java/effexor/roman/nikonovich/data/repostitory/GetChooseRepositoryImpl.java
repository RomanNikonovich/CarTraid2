package effexor.roman.nikonovich.data.repostitory;


import java.util.List;

import javax.inject.Inject;

import effexor.roman.nikonovich.data.entity.entityChooseNet.MakeCarNet;
import effexor.roman.nikonovich.data.entity.entityChooseNet.ModelNet;
import effexor.roman.nikonovich.data.entity.entityChooseRealm.MakeCarRealm;
import effexor.roman.nikonovich.data.restApi.RestService;
import effexor.roman.nikonovich.data.utils.convertData.ConvertToDomainData;
import effexor.roman.nikonovich.data.utils.convertData.ConvertToRealm;
import effexor.roman.nikonovich.domain.entity.entityChoose.MakeCar;
import effexor.roman.nikonovich.domain.repository.GetChooseRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;

public class GetChooseRepositoryImpl implements GetChooseRepository {
    private RestService restService;
    private Realm realm;

    //FIXME - подумать обработку ошибок от реаль, может сюда запихнуть compose и удалить RestService
    @Inject
    public GetChooseRepositoryImpl(RestService restService) {
        this.restService = restService;

    }

    @Override
    public Completable loadChoose() {

        return restService
                .loadMakes()
                .doOnNext(new Consumer<List<MakeCarNet>>() {
                    @Override
                    public void accept(final List<MakeCarNet> makeCarNets) throws Exception {
                        for(final MakeCarNet carNet: makeCarNets){
                            restService
                                    .loadModels(carNet.getObjectId())
                                    .subscribe(new Consumer<List<ModelNet>>() {
                                        @Override
                                        public void accept(List<ModelNet> modelNets) throws Exception {
                                            carNet.setModelsCars(modelNets);
                                        }
                                    });
                        }

                    }
                })
                .map(new Function<List<MakeCarNet>, List<MakeCarRealm>>() {

                    @Override
                    public List<MakeCarRealm> apply(List<MakeCarNet> makeCarNetNets) throws Exception {
                        return ConvertToRealm.convertList(makeCarNetNets);
                    }
                })
                .doOnNext(new Consumer<List<MakeCarRealm>>() {
                    @Override
                    public void accept(List<MakeCarRealm> list) throws Exception {
                        realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.insert(list);
                        realm.commitTransaction();
                        realm.close();
                    }
                })
                .ignoreElements();

    }

    @Override
    public Flowable<List<MakeCar>>  getChoose() {
        realm = Realm.getDefaultInstance();
        return realm
                .where(MakeCarRealm.class)
                .findAllAsync()
                .asFlowable()
                .take(1)
                .map(new Function<RealmResults<MakeCarRealm>, List<MakeCar>>() {
                    @Override
                    public List<MakeCar> apply(RealmResults<MakeCarRealm> makeCarRealms) throws Exception {
                        return ConvertToDomainData.convertList(makeCarRealms);
                    }
                });

    }
}

