package effexor.roman.nikonovich.data.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet;
import io.realm.RealmList;

public class ParseUrl {
    private static final String SELECT_GET_CARS = "div.listing-item-firm, div.listing-item";
    private static final String SELECT_GET_PAGES = "li.pages-numbers-item";
    private static final String SELECT_GET_USD = "small";
    private static final String SELECT_GET_RUB = "strong";
    private static final String SELECT_GET_SIBLING = "h4>a[href]";
    private static final String SELECT_GET_CURSE = "td.course";
    private static final String SELECT_COUNT_PAGES = "a[href]";
    private static final String ATTR = "href";
    private static final String URL_PRICE_USD = "https://myfin.by/bank/kursy_valjut_nbrb";
    private static double curseUSD = 0;


    public static RealmList<VehicleNet> getCars(String url) throws IOException {
        RealmList<VehicleNet> carsList = new RealmList<>();
        Document docCars = Jsoup
                    .connect(url)
                    .get();
        
        Elements cars = docCars
                .select(SELECT_GET_CARS);
        Elements urlPages = docCars
                .select(SELECT_GET_PAGES);
        if (urlPages.size() != 0) {
            carsList.addAll(parsePage(cars));
            for (int i = 1; i < urlPages.size(); i++) {
                carsList.addAll(parsePages(urlPages.get(i).select(SELECT_COUNT_PAGES).attr(ATTR)));
            }
        } else {
            carsList.addAll(parsePage(cars));
        }
        return carsList;
    }

    private static RealmList<VehicleNet> parsePages(String urlP) throws IOException {
        Document docCarsPage = Jsoup
                .connect(urlP)
                .get();
        Elements cars = docCarsPage
                .select(SELECT_GET_CARS);
        return parsePage(cars);
    }

    private static RealmList<VehicleNet> parsePage(Elements cars) throws IOException {
        RealmList<VehicleNet> carsList = new RealmList<>();

        for (Element element : cars) {
            int priceUSD = 0;
            if (element.select(SELECT_GET_USD).get(0).text() != null ||
                    !element.select(SELECT_GET_USD).get(0).text().equals("")) {
                if (curseUSD == 0) {
                    curseUSD = getCursUSD();
                }
                priceUSD = (int)(Integer.valueOf(element.select(SELECT_GET_RUB).get(0).text()
                        .replaceAll("\\D", ""))/curseUSD);
            }
            carsList.add(new VehicleNet(
                    element.select(SELECT_GET_SIBLING).attr(ATTR),
                    element.select(SELECT_GET_SIBLING).text(),
                    element.select(SELECT_GET_RUB).get(0).text(),
                    priceUSD));
        }
        return carsList;
    }

    private static double getCursUSD() throws IOException {
        try {
            Document docCars = Jsoup
                    .connect(URL_PRICE_USD)
                    .get();
            return Double.valueOf(docCars
                    .select(SELECT_GET_CURSE).get(0).text());
        } catch (Exception e){

        } finally {
            return 2.6;
        }


    }
}
