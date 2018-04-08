package effexor.roman.nikonovich.domain.entity.entityChoose;


import java.util.List;

public class MakeCar {
    private String makeCar;
    private int idCar;
    private List<Model> modelsCars;

    public MakeCar(String makeCar, int idCar) {
        this.makeCar = makeCar;
        this.idCar = idCar;
    }

    public void setModelsCars(List<Model> modelsCars) {
        this.modelsCars = modelsCars;
    }

    public String getMakeCar() {

        return makeCar;
    }

    public int getIdCar() {
        return idCar;
    }

    public List<Model> getModelsCars() {
        return modelsCars;
    }

    @Override
    public String toString() {
        return "MakeCar{" +
                "makeCar='" + makeCar + '\'' +
                ", idCar=" + idCar +
                ", modelsCars=" + modelsCars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MakeCar makeCar1 = (MakeCar) o;

        if (idCar != makeCar1.idCar) return false;
        if (makeCar != null ? !makeCar.equals(makeCar1.makeCar) : makeCar1.makeCar != null)
            return false;
        return modelsCars != null ? modelsCars.equals(makeCar1.modelsCars) : makeCar1.modelsCars == null;
    }

    @Override
    public int hashCode() {
        int result = makeCar != null ? makeCar.hashCode() : 0;
        result = 31 * result + idCar;
        result = 31 * result + (modelsCars != null ? modelsCars.hashCode() : 0);
        return result;
    }

}
