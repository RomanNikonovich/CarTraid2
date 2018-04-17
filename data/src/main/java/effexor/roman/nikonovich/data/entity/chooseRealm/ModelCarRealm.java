package effexor.roman.nikonovich.data.entity.chooseRealm;

import io.realm.RealmObject;

public class ModelCarRealm extends RealmObject{
    private String modelCar;
    private int idModel;

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public int getIdModel() {
        return idModel;
    }

    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }
}
