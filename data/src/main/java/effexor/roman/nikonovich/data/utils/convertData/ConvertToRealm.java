package effexor.roman.nikonovich.data.utils.convertData;

import java.util.ArrayList;
import java.util.List;

import effexor.roman.nikonovich.data.entity.chooseNet.MakeCarNet;
import effexor.roman.nikonovich.data.entity.chooseNet.ModelNet;
import effexor.roman.nikonovich.data.entity.chooseRealm.MakeCarRealm;
import effexor.roman.nikonovich.data.entity.chooseRealm.ModelCarRealm;
import io.realm.RealmList;

public class ConvertToRealm {

    public static List<MakeCarRealm> convertList(List<MakeCarNet> makeCarNetNets) {

        List<MakeCarRealm> carRealms = new ArrayList<>();
        RealmList<ModelCarRealm> modelCarRealms;
        MakeCarRealm makeCarRealm;
        ModelCarRealm modelCarRealm;
        for (MakeCarNet make : makeCarNetNets) {
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
