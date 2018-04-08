package effexor.roman.nikonovich.data.entity.entityChooseRealm;

import io.realm.RealmList;
import io.realm.RealmObject;

public class MakeCarRealm extends RealmObject {
    private String makeCar;
    private int idCar;
    private RealmList<ModelCarRealm> modelsCars;

    public String getMakeCar() {
        return makeCar;
    }

    public void setMakeCar(String makeCar) {
        this.makeCar = makeCar;
    }

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public RealmList<ModelCarRealm> getModelsCars() {
        return modelsCars;
    }

    public void setModelsCars(RealmList<ModelCarRealm> modelsCars) {
        this.modelsCars = modelsCars;
    }
}
