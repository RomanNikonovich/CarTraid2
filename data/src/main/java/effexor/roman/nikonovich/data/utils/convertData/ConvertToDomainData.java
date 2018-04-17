package effexor.roman.nikonovich.data.utils.convertData;

import java.util.ArrayList;
import java.util.List;

import effexor.roman.nikonovich.data.entity.chooseRealm.MakeCarRealm;
import effexor.roman.nikonovich.data.entity.chooseRealm.ModelCarRealm;
import effexor.roman.nikonovich.domain.entity.choose.MakeCar;
import effexor.roman.nikonovich.domain.entity.choose.Model;
import io.realm.RealmList;

public class ConvertToDomainData {
    public static List<MakeCar> convertList(List<MakeCarRealm> makeCarRealms) {

        List<MakeCar> cars = new ArrayList<>();//
        List<Model> models;
        MakeCar makeCar;//
        Model modelCar;
        for (MakeCarRealm makeRealm : makeCarRealms) {
            makeCar = new MakeCar(makeRealm.getMakeCar(), makeRealm.getIdCar());//
            models = new RealmList<>();
            for (ModelCarRealm modelRealm : makeRealm.getModelsCars()) {
                modelCar = new Model(modelRealm.getModelCar(), modelRealm.getIdModel());
                models.add(modelCar);
            }
            makeCar.setModelsCars(models);
            cars.add(makeCar);
        }
        return cars;
    }
}
