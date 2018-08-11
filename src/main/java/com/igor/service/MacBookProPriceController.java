package com.igor.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by igor on 2018-08-11.
 */
public abstract class MacBookProPriceController {

    public static final String DATA_GROUP_13 = "13inch";
    public static final String DATA_GROUP_15 = "15inch";

    protected List<Product> loadMacBookPros() throws IOException {


        final List<Product> products = getProducts(getType13Inch());

        products.addAll(getProducts(getType15Inch()));

        return products;
    }

    private List getProducts(String type) throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());
        final String dataGroup;

        if (type.equals(getType13Inch())) {
            dataGroup = DATA_GROUP_13;
        } else if (type.equals(getType15Inch())) {
            dataGroup = DATA_GROUP_15;
        } else {
            throw new RuntimeException("Type does not exist");
        }

        final Optional<HtmlDivision> div = page.getByXPath("//div[@data-group='" + dataGroup + "']")
                .stream()
                .map(HtmlDivision.class::cast)
                .findFirst();

        final List<HtmlDivision> tags = div.get().getByXPath("*//div[@class='as-macbtr-optioncontent']")
                .stream()
                .map(c -> (HtmlDivision) c)
                .filter(c -> ((HtmlDivision) c.getParentNode()).getAttribute("class").contains("modelshown"))
                .collect(Collectors.toList());

        final List<Product> products = new ArrayList<>();

        tags.forEach(tag -> {

            final HtmlHeading3 h3 = tag.getByXPath("h3")
                    .stream()
                    .map(HtmlHeading3.class::cast)
                    .findFirst().get();

            final String model = type + " " + h3.getChildNodes()
                    .stream()
                    .map(s -> s.toString())
                    .filter(s -> !s.contains("<br>"))
                    .collect(Collectors.joining(" ")).replace("  ", " ");

            final Optional<HtmlSpan> span = tag.getByXPath("*//span[@class='as-price-currentprice']/span")
                    .stream()
                    .map(HtmlSpan.class::cast)
                    .findFirst();

            Number price;

            try {
                price = parsePrice(span.get().getTextContent());
            } catch (ParseException e) {
                price = 0;
                e.printStackTrace();
            }

            products.add(new Product(model, price, Currency.getInstance(getLocale())));
        });

        return products;
    }

    private WebClient getClient() {

        final WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        return client;
    }


    protected abstract String getURL();

    protected abstract String getType13Inch();

    protected abstract String getType15Inch();

    protected abstract Locale getLocale();

    protected abstract Number parsePrice(String priceString) throws ParseException;

}
