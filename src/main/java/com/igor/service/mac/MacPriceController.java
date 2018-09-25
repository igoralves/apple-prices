package com.igor.service.mac;

import com.gargoylesoftware.htmlunit.html.*;
import com.igor.model.Product;
import com.igor.service.PriceController;
import com.igor.xml.Country;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MacPriceController extends PriceController {

    protected List<Product> createProducts(List<HtmlDivision> tags, String modelPrefix, Country country) {

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

            final Float price = getPrice(tag, country);

            products.add(new Product(model, price, country.getCurrency()));
        });

        return products;
    }

    protected Product createProduct(HtmlPage page, String model, Country country) {

        final Float price = getPrice(page, country);

        return new Product(model, price, country.getCurrency());
    }

    private Float getPrice(DomNode tag, Country country) {

        final Optional<HtmlSpan> span = tag.getByXPath("*//span[@class='as-price-currentprice']/span")
                .stream()
                .map(HtmlSpan.class::cast)
                .findFirst();

        Float price;

        try {
            price = parsePrice(span.get().getTextContent(), country);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return price;
    }

    private Float parsePrice(String priceString, Country country) throws ParseException {

        final NumberFormat numberFormat = country.getNumberFormat();

        return numberFormat.parse(priceString.trim()).floatValue();
    }
}
