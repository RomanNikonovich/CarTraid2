package effexor.roman.nikonovich.data.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet;
import io.realm.RealmList;

public class ParseUrl {
    private static final String SELECT_GET_CARS = "div.listing-item";
    private static final String SELECT_GET_USD = "div.listing-item__priceusd";
    private static final String SELECT_GET_RUB = "div.listing-item__price";
    private static final String ATTR = "href";
    private static final String PRIME_URL = "https://cars.av.by";
    private static final String SELECT_GET_URL = "a.listing-item__link";
    private static final String SELECT_GET_MODEL = "span.link-text";


    public static RealmList<VehicleNet> getCars(String url) throws IOException {

        RealmList<VehicleNet> carsList = new RealmList<>();
        Document docCars = Jsoup
                .connect(url)
                .get();

        Elements cars = docCars
                .select(SELECT_GET_CARS);

        carsList.addAll(parsePage(cars));
        carsList.addAll(parsePages(url));

        return carsList;

    }

    private static RealmList<VehicleNet> parsePages(String urlP) throws IOException {
        RealmList<VehicleNet> carsList = new RealmList<>();

        int i = 2;
        while (true) {
            Document docCarsPages = Jsoup
                    .connect(urlP + "&page=" + i)
                    .get();
            Elements carsPage = docCarsPages
                    .select(SELECT_GET_CARS);
            if (carsPage.size() != 0) {
                carsList.addAll(parsePage(carsPage));
                i++;
            } else {
                return carsList;
            }

        }

    }

    private static RealmList<VehicleNet> parsePage(Elements cars) throws IOException {
        RealmList<VehicleNet> carsList = new RealmList<>();

        for (Element element : cars) {
            int priceUSD = (int) (Integer.valueOf(element.select(SELECT_GET_USD).text()
                    .replaceAll("\\D", "")));
            carsList.add(new VehicleNet(
                    PRIME_URL + element.select(SELECT_GET_URL).attr(ATTR),
                    element.select(SELECT_GET_MODEL).text(),
                    element.select(SELECT_GET_RUB).text(),
                    priceUSD));
        }
        return carsList;
    }

}
