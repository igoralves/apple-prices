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
 * Created by igor on 2018-08-02.
 */
@RestController
public class MacBookProPriceController {

    public static final String URL = "https://www.apple.com/shop/buy-mac/macbook-pro";

    @RequestMapping("/macbookpro")
    public List<Product> loadMacBookPros() throws IOException {

        final ArrayList<Product> products = new ArrayList<>();

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(URL);

        final Optional<HtmlDivision> div15Inch = page.getByXPath("//div[@data-group='15inch']")
                .stream()
                .map(HtmlDivision.class::cast)
                .findFirst();

        final List<HtmlDivision> tags = div15Inch.get().getByXPath("*//div[@class='as-macbtr-optioncontent']")
                .stream()
                .map(c -> (HtmlDivision) c)
                .filter(c -> ((HtmlDivision) c.getParentNode()).getAttribute("class").contains("modelshown"))
                .collect(Collectors.toList());

        tags.forEach(tag -> {

            final String model = tag.getByXPath("h3")
                    .stream()
                    .map(HtmlHeading3.class::cast)
                    .findFirst().get().getTextContent().replace("  ", " ");


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

            products.add(new Product(model, price, Currency.getInstance("PLN")));
        });


        return products;
    }

    private Number parsePrice(String priceString) throws ParseException {

        priceString = priceString.trim().replace("$", "");

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);

        return nf.parse(priceString);
    }

    private WebClient getClient() {

        final WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        return client;
    }
}
