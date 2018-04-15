package effexor.roman.nikonovich.presentation.screens.formFilling.utils;

public class SearchingURL {
    private static final String URL_PART_1 = "https://cars.av.by/search?brand_id%5B%5D=";
    private static final String URL_PART_2 = "&model_id%5B%5D=";
    private static final String URL_PART_3 = "&year_from=";
    private static final String URL_PART_4 = "&year_to=";
    private static final String URL_PART_5 = "&currency=USD&price_from=&price_to=";

   public static String getURL(String idMake, String idModel, String yearFrom, String yearTo) {
        StringBuilder url = new StringBuilder();
        url.append(URL_PART_1);
        url.append(idMake);
        url.append(URL_PART_2);
        url.append(idModel);
        url.append(URL_PART_3);
        if (!yearFrom.equals("")) {
            url.append(yearFrom);
            url.append(URL_PART_4);
        } else {
            url.append(URL_PART_4);
        }
        if (!yearTo.equals("")) {
            url.append(yearTo);
            url.append(URL_PART_5);
        } else {
            url.append(URL_PART_5);
        }
        return url.toString();
    }
}
