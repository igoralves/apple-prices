package com.igor.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading3;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by igor on 2018-08-02.
 */
@RestController
public class MacBookProPriceController {

    public static final String URL = "https://www.apple.com/shop/buy-mac/macbook-pro";

    @RequestMapping("/macbookpro")
    public List<Product> greeting() throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = getClient().getPage(URL);

        final HtmlDivision div13Inch = page.getByXPath("//div[@data-group='15inch']")
                .stream()
                .map(HtmlDivision.class::cast)
                .findFirst().get();


//        final List<HtmlDivision> tags = div13Inch.getByXPath("*//div[@class='as-macbtr-optioncontent']")
//                .stream()
//                .map(c -> (HtmlDivision) c)
//                .filter(c -> ((HtmlDivision) c.getParentNode()).getAttribute("class").contains("modelshown"))
//                .collect(Collectors.toList());
//
//        tags.forEach(tag -> {
//
//            final String model = tag.getByXPath("h3")
//                    .stream()
//                    .map(HtmlHeading3.class::cast)
//                    .findFirst().get().getTextContent().replace("  ", " ");
//
//
//            final Optional<HtmlSpan> span = tag.getByXPath("*//span[@class='as-price-currentprice']/span")
//                    .stream()
//                    .map(HtmlSpan.class::cast)
//                    .findFirst();
//
//            final String price = span.get().getTextContent().trim();
//
//
//            System.out.println(model + " ===> " + price);
//        });














        final ArrayList<Product> products = new ArrayList<>();

        products.add(new Product("Model...", 1000F, Currency.getInstance("PLN")));
        products.add(new Product("Model...", 1000F, Currency.getInstance("PLN")));

        return products;
    }

    private WebClient getClient() {

        final WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        return client;
    }



    public void main(String[] args) throws Exception {



        final HtmlPage page = getClient().getPage(URL);

        final HtmlDivision div13Inch = page.getByXPath("//div[@data-group='15inch']")
                .stream()
                .map(HtmlDivision.class::cast)
                .findFirst().get();

        System.out.println(div13Inch);


//
    }
}
