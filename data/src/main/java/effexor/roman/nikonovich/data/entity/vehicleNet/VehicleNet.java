package effexor.roman.nikonovich.data.entity.vehicleNet;

import java.util.Objects;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class VehicleNet extends RealmObject {
    @PrimaryKey
    @Required
    private String url;
    private String make;
    private String priceRUB;
    private String priceUSD;

    public VehicleNet() {
    }

    public VehicleNet(String url, String make, String priceRUB, String priceUSD) {
        this.url = url;
        this.make = make;
        this.priceRUB = priceRUB;
        this.priceUSD = priceUSD;
    }

    public String getUrl() {
        return url;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getPriceRUB() {
        return priceRUB;
    }

    public void setPriceRUB(String priceRUB) {
        this.priceRUB = priceRUB;
    }

    public String getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(String priceUSD) {
        this.priceUSD = priceUSD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleNet that = (VehicleNet) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url);
    }

}
