package effexor.roman.nikonovich.domain.entity.vehicle;

public class Car {
    private String url;
    private String make;
    private String priceRUB;
    private String priceUSD;

    public Car(String url, String make, String priceRUB, String priceUSD) {
        this.url = url;
        this.make = make;
        this.priceRUB = priceRUB;
        this.priceUSD = priceUSD;
    }
}
