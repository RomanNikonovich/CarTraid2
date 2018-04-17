package effexor.roman.nikonovich.data.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import effexor.roman.nikonovich.data.entity.vehicleNet.VehicleNet;
import io.realm.RealmList;

public class ParseUrl {
    public static RealmList<VehicleNet> getCars(String url) throws IOException {
        RealmList<VehicleNet> carsList = new RealmList<>();
        Document docCars = Jsoup
                .connect(url)
                .get();
        Elements cars = docCars
                .select("div.listing-item-firm, div.listing-item");
        Elements urlPages = docCars
                .select("li.pages-numbers-item");
        if (urlPages.size() != 0) {
            carsList.addAll(parsePage(cars));
            for (int i = 1; i < urlPages.size(); i++) {
                carsList.addAll(parsePages(urlPages.get(i).select("a[href]").attr("href")));
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
                .select("div.listing-item-firm, div.listing-item");
        return parsePage(cars);
    }

    private static RealmList<VehicleNet> parsePage(Elements cars) {
        RealmList<VehicleNet> carsList = new RealmList<>();
        for (Element element : cars) {
            carsList.add(new VehicleNet(
                    element.select("h4>a[href]").attr("href"),
                    element.select("h4>a[href]").text(),
                    element.select("strong").get(0).text(),
                    element.select("small").get(0).text()));
        }
        return carsList;
    }
}
