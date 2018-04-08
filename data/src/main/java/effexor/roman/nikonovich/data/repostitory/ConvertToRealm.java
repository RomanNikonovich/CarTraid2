package effexor.roman.nikonovich.data.repostitory;

import java.util.ArrayList;
import java.util.List;

import effexor.roman.nikonovich.data.entity.entityChooseNet.MakeCarNet;
import effexor.roman.nikonovich.data.entity.entityChooseNet.ModelNet;
import effexor.roman.nikonovich.data.entity.entityChooseRealm.MakeCarRealm;
import effexor.roman.nikonovich.data.entity.entityChooseRealm.ModelCarRealm;
import io.realm.RealmList;

public class ConvertToRealm {

    public static List<MakeCarRealm> convertList(List<MakeCarNet> makeCarNets) {

        List<MakeCarRealm> carRealms = new ArrayList<>();
        RealmList<ModelCarRealm> modelCarRealms;
        MakeCarRealm makeCarRealm;
        ModelCarRealm modelCarRealm;
        for (MakeCarNet make : makeCarNets) {
            makeCarRealm = new MakeCarRealm();
            makeCarRealm.setIdCar(make.getIdCar());
            makeCarRealm.setMakeCar(make.getMakeCar());
            modelCarRealms = new RealmList<>();
            for (ModelNet model : make.getModelsCars()) {
                modelCarRealm = new ModelCarRealm();
                modelCarRealm.setIdModel(model.getIdModel());
                modelCarRealm.setModelCar(model.getModelCar());
                modelCarRealms.add(modelCarRealm);
            }
            makeCarRealm.setModelsCars(modelCarRealms);
            carRealms.add(makeCarRealm);
        }
        return carRealms;
    }
}
