package effexor.roman.nikonovich.domain.entity.entityChoose;


public class Model implements Comparable<Model>{
    private String modelCar;
    private int idModel;

    public Model(String modelCar, int idModel) {

        this.modelCar = modelCar;
        this.idModel = idModel;
    }

    public String getModelCar() {

        return modelCar;
    }

    public int getIdModel() {
        return idModel;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (idModel != model.idModel) return false;
        return modelCar != null ? modelCar.equals(model.modelCar) : model.modelCar == null;
    }

    @Override
    public int hashCode() {
        int result = modelCar != null ? modelCar.hashCode() : 0;
        result = 31 * result + idModel;
        return result;
    }

    @Override
    public int compareTo(Model model) {
        return this.getModelCar().compareTo(model.getModelCar());
    }
}
