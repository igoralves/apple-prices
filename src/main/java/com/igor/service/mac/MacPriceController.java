package com.igor.service.mac;

import com.gargoylesoftware.htmlunit.html.*;
import com.igor.service.PriceController;
import com.igor.service.Product;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MacPriceController extends PriceController {

    protected List<Product> createProducts(List<HtmlDivision> tags, String modelPrefix) {

        final List<Product> products = new ArrayList<>();

        tags.forEach(tag -> {

            final HtmlHeading3 h3 = tag.getByXPath("h3")
                    .stream()
                    .map(HtmlHeading3.class::cast)
                    .findFirst().get();

            final String model = modelPrefix + " " + h3.getChildNodes()
                    .stream()
                    .map(Object::toString)
                    .filter(s -> !s.contains("<br>"))
                    .collect(Collectors.joining(" ")).replace("  ", " ");

            final Float price = getPrice(tag);

            products.add(new Product(model, price, Currency.getInstance(getLocale())));
        });

        return products;
    }

    private Float getPrice(DomNode tag) {

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
        return price;
    }

    protected Product createProduct(HtmlPage page, String model) {

        final Float price = getPrice(page);

        return new Product(model, price, Currency.getInstance(getLocale()));
    }

    private Float parsePrice(String priceString) throws ParseException {

        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(getLocale());

        return numberFormat.parse(priceString.trim()).floatValue();
    }

    protected abstract String getURL();

    protected abstract Locale getLocale();
}
