package effexor.roman.nikonovich.domain.entity.vehicle;

public class Vehicle {
    private String url;
    private String make;
    private String priceRUB;
    private String priceUSD;

    public Vehicle(String url, String make, String priceRUB, String priceUSD) {
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

    public String getPriceRUB() {
        return priceRUB;
    }

    public String getPriceUSD() {
        return priceUSD;
    }


}
