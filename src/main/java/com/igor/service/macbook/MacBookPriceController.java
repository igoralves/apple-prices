package com.igor.service.macbook;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.igor.service.Product;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by igor on 2018-08-11.
 */
public abstract class MacBookPriceController {

    protected List<Product> loadMacBooks() throws IOException {

        final List<Product> products = getProducts();

        return products;
    }

    private List getProducts() throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());

        final List<HtmlDivision> tags = page.getByXPath("*//div[@class='as-macbtr-optioncontent']")
                .stream()
                .map(c -> (HtmlDivision) c)
                .filter(c -> ((HtmlDivision) c.getParentNode()).getAttribute("class").contains("modelshown"))
                .collect(Collectors.toList());

//        final List<?> byXPath = page.getByXPath("*//h2");


        final String modelPrefix = page.getByXPath("*//h2[@class='as-bundleselection-modeltitle']")
                .stream()
                .map(HtmlHeading2.class::cast)
                .findFirst().get().getTextContent();

        final List<Product> products = new ArrayList<>();

        tags.forEach(tag -> {

            final HtmlHeading3 h3 = tag.getByXPath("h3")
                    .stream()
                    .map(HtmlHeading3.class::cast)
                    .findFirst().get();

            final String model = modelPrefix + " " + h3.getChildNodes()
                    .stream()
                    .map(s -> s.toString())
                    .filter(s -> !s.contains("<br>"))
                    .collect(Collectors.joining(" ")).replace("  ", " ");

            final Optional<HtmlSpan> span = tag.getByXPath("*//span[@class='as-price-currentprice']/span")
                    .stream()
                    .map(HtmlSpan.class::cast)
                    .findFirst();

            Float price;

            try {
                price = parsePrice(span.get().getTextContent());
            } catch (ParseException e) {
                throw new RuntimeException(e);
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

    private Float parsePrice(String priceString) throws ParseException {

        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(getLocale());

        return numberFormat.parse(priceString.trim()).floatValue();
    }

    protected abstract String getURL();

    protected abstract Locale getLocale();


}
