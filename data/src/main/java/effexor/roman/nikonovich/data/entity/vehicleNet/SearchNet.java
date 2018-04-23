package effexor.roman.nikonovich.data.entity.vehicleNet;

import java.util.Objects;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class SearchNet extends RealmObject {
    @PrimaryKey
    @Required
    private String idSearch = UUID.randomUUID().toString();
    private String nameSearch;
    private String urlSearch;
    private long dateCreate = System.currentTimeMillis();
    private int price;
    private RealmList<VehicleNet> listVehicleNet = new RealmList<>();

    public SearchNet() {
    }

    public SearchNet(String nameSearch, String urlSearch, int price) {
        this.nameSearch = nameSearch;
        this.urlSearch = urlSearch;
        this.price = price;

    }

    public int getPrice() {
        return price;
    }

    public long getDateCreate() {
        return dateCreate;
    }

    public RealmList<VehicleNet> getListVehicleNet() {
        return listVehicleNet;
    }

    public void setListVehicleNet(RealmList<VehicleNet> listVehicleNet) {
        this.listVehicleNet = listVehicleNet;
    }

    public String getUrlSearch() {
        return urlSearch;
    }

    public void setUrlSearch(String urlSearch) {
        this.urlSearch = urlSearch;
    }

    public String getIdSearch() {

        return idSearch;
    }

    public String getNameSearch() {

        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchNet that = (SearchNet) o;
        return Objects.equals(idSearch, that.idSearch);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idSearch);
    }
}
