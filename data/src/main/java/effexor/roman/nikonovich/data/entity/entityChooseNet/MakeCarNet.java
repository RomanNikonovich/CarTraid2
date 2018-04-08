package effexor.roman.nikonovich.data.entity.entityChooseNet;

import java.util.List;

public class MakeCarNet {
    private String makeCar;
    private int idCar;
    private List<ModelNet> modelsCars;

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

    public List<ModelNet> getModelsCars() {
        return modelsCars;
    }

    public void setModelsCars(List<ModelNet> modelsCars) {
        this.modelsCars = modelsCars;
    }
}
