package effexor.roman.nikonovich.presentation.screens.formFilling.utils;

public class SearchingURL {
    private static final String URL_PART_1 = "https://cars.av.by/filter?brands[0][brand]=";
    private static final String URL_PART_2 = "&brands[0][model]=";
    private static final String URL_PART_3 = "&year[min]=";
    private static final String URL_PART_4 = "&year[max]=";

    public static String getURL(String idMake, String idModel, String yearFrom, String yearTo) {
        StringBuilder url = new StringBuilder();
        url.append(URL_PART_1);
        url.append(idMake);
        url.append(URL_PART_2);
        url.append(idModel);
        if (!yearFrom.equals("") && yearTo.equals("")) {
            url.append(URL_PART_3);
            url.append(yearFrom);
        }
        if (yearFrom.equals("") && !yearTo.equals("")) {
            url.append(URL_PART_4);
            url.append(yearTo);
        }

        if (!yearFrom.equals("") && !yearTo.equals("")) {
            url.append(URL_PART_3);
            url.append(yearFrom);
            url.append(URL_PART_4);
            url.append(yearTo);
        }
        return url.toString();
    }
}
