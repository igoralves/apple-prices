package com.igor.service.ipad;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.igor.service.PriceController;
import com.igor.service.Product;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

public abstract class IpadPriceController extends PriceController {

    private static final String MODEL_32GB = "32 GB";
    private static final String MODEL_128GB = "128 GB";

    protected List<Product> getProducts() throws IOException {

        final HtmlPage firstPage = getFirstPage();

        final Optional<HtmlInput> input = firstPage
                .getByXPath("//input[@id='dimensionColor-silver']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage pageAfterChoosingColor = input.get().click();

        final List<Product> products = new ArrayList<>();

        products.addAll(getProductsByCapacity(pageAfterChoosingColor, MODEL_32GB));
        products.addAll(getProductsByCapacity(pageAfterChoosingColor, MODEL_128GB));

        return products;
    }

    private HtmlPage getFirstPage() throws IOException {

        final WebClient client = getClient();

        client.getOptions().setJavaScriptEnabled(true);

        // this was necessary in order to give more time for loading the page after click
        client.waitForBackgroundJavaScript(20000);

        // this was necessary due to a bug in Google Cloud (https://issuetracker.google.com/issues/35889390)
        client.setCookieManager(createCookieManager());

        return client.getPage(getURL());
    }

    private CookieManager createCookieManager() {
        return new CookieManager() {
            protected int getPort(URL url) {

                final int port = super.getPort(url);

                return port != -1 ? port : 80;
            }
        };
    }

    private List<Product> getProductsByCapacity(HtmlPage page, String capacity) throws IOException {

        final String modelString = capacity.replace(" ", "").toLowerCase();

        final Optional<HtmlInput> input = page
                .getByXPath("//input[@id='Item2-dimensionCapacity-" + modelString + "']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage pageAfterChoosingCapacity = input.get().click();

        final List<HtmlDivision> divs = pageAfterChoosingCapacity
                .getByXPath("//div[@id='Item3']//div[@class='form-element ']");

        return createProducts(divs, capacity);
    }

    private List<Product> createProducts(List<HtmlDivision> divs, String capacity) {

        final List<Product> products = new ArrayList<>();

        divs.forEach(div -> {

            final Optional<HtmlSpan> modelSpan = div.getByXPath("*//span[@class='form-selector-title']")
                    .stream()
                    .map(HtmlSpan.class::cast)
                    .findFirst();

            final String model = "iPad " + capacity + " " + modelSpan.get().getTextContent();

            final Optional<HtmlSpan> priceSpan = div.getByXPath("*//span[@class='']")
                    .stream()
                    .map(HtmlSpan.class::cast)
                    .findFirst();

            final Float price;

            try {
                price = parsePrice(priceSpan.get().getTextContent());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            products.add(new Product(model, price, Currency.getInstance(getLocale())));

        });

        return products;
    }
}
