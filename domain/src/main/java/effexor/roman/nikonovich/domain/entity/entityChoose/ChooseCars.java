package effexor.roman.nikonovich.domain.entity.entityChoose;


import java.util.List;

public class ChooseCars {
    private List<MakeCar> makeCars;

    public ChooseCars(List<MakeCar> makeCars) {
        this.makeCars = makeCars;
    }

    public List<MakeCar> getMakeCars() {
        return makeCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChooseCars that = (ChooseCars) o;

        return makeCars != null ? makeCars.equals(that.makeCars) : that.makeCars == null;
    }

    @Override
    public int hashCode() {
        return makeCars != null ? makeCars.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ChooseCars{" +
                "makeCars=" + makeCars +
                '}';
    }
}
