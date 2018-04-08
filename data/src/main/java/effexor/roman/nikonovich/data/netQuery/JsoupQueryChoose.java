package effexor.roman.nikonovich.data.netQuery;/*
package effexor.roman.nikonovich.data.netQuery;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import effexor.roman.nikonovich.data.entity.entityChooseRealm.MakeCarRealm;
import effexor.roman.nikonovich.data.entity.entityChooseRealm.ModelNet;

public class JsoupQueryChoose {
    private static final String URL = "https://av.by/";
    private static final String SELECT_MAKES = "option:containsOwn(Марка автомобиля)";
    private static final String ATTR_VALUE = "value";
    private static final String FIR_PART_URL = "https://cars.av.by/search?brand_id%5B%5D=";
    private static final String SEC_PART_URL = "&model_id%5B%5D=0&year_from=&year_to=&currency=USD&price_from=&price_to=";
    private static final String SELECT_MARK = "option:containsOwn(Модель)";


    public static List<MakeCarRealm> getData() throws IOException {

        Document docMake = Jsoup
                .connect(URL)
                .get();
        Document docModel;

        Elements makes = docMake
                .select(SELECT_MAKES)
                .get(0)
                .siblingElements();

        List<MakeCarRealm> listMakes = new ArrayList<>();
        MakeCarRealm makeCarRealm;

        for (Element make : makes) {

           */
/* makeCarRealm = new MakeCarRea(make.text(), Integer.valueOf(make.attr(ATTR_VALUE)));*//*

            List<ModelNet> listModels = new ArrayList<>();

            docModel = Jsoup.connect(FIR_PART_URL + make.attr(ATTR_VALUE) + SEC_PART_URL).get();

            Elements models = docModel
                    .select(SELECT_MARK)
                    .get(0)
                    .siblingElements();

            for (Element model : models) {
                ModelNet modelNet = new ModelNet(model.text(), Integer.valueOf(model.attr(ATTR_VALUE)));
                listModels.add(modelNet);
            }
         */
/*   makeCarRealm.setModelsCarsR(listModels);*//*

          */
/*  listMakes.add(makeCarRealm);*//*

        }
        return listMakes;
    }
}
*/
