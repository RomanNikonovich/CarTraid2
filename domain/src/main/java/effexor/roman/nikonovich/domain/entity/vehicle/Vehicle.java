package effexor.roman.nikonovich.domain.entity.vehicle;

public class Vehicle implements Comparable<Vehicle> {
    private String url;
    private String make;
    private String priceRUB;
    private int priceUSD;
    private int priceLow;

    public Vehicle(String url, String make, String priceRUB, int priceUSD, int priceLow) {
        this.url = url;
        this.make = make;
        this.priceRUB = priceRUB;
        this.priceUSD = priceUSD;
        this.priceLow = priceLow;
    }

    public int getPriceLow() {
        return priceLow;
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

    public int getPriceUSD() {
        return priceUSD;
    }


    @Override
    public int compareTo(Vehicle vehicle) {
        return this.priceUSD - vehicle.priceUSD;
    }
}
