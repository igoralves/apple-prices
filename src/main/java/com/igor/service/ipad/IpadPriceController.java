package com.igor.service.ipad;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.igor.service.Countries;
import com.igor.service.PriceController;
import com.igor.service.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

@RestController
public class IpadPriceController extends PriceController {

    private static final String URL = "https://www.apple.com/%s/shop/buy-ipad/ipad-9-7";
    private static final String MODEL_32GB = "32 GB";
    private static final String MODEL_128GB = "128 GB";

    @RequestMapping("/{countryCode}/ipad")
    public List<Product> getProducts(@PathVariable String countryCode) throws IOException {

        final Locale locale = getLocale(countryCode);
        final String url = getURL(countryCode);

        final HtmlPage firstPage = getFirstPage(url);

        final Optional<HtmlInput> input = firstPage
                .getByXPath("//input[@id='dimensionColor-silver']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage pageAfterChoosingColor = input.get().click();

        final List<Product> products = new ArrayList<>();

        products.addAll(getProductsByCapacity(pageAfterChoosingColor, MODEL_32GB, locale));
        products.addAll(getProductsByCapacity(pageAfterChoosingColor, MODEL_128GB, locale));

        return products;
    }

    private HtmlPage getFirstPage(String url) throws IOException {

        final WebClient webClient = getClient();

        // necessary for all iPad pages
        webClient.getOptions().setJavaScriptEnabled(true);

        // necessary for some stores (e.g. Hungary)
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);

        // necessary due to a bug in Google Cloud (https://issuetracker.google.com/issues/35889390)
        webClient.setCookieManager(createCookieManager());

        final HtmlPage page = webClient.getPage(url);

        // necessary in order to give more time for loading the page after click
        webClient.waitForBackgroundJavaScript(20000);
        webClient.waitForBackgroundJavaScriptStartingBefore(20000);

        return page;
    }

    private List<Product> getProductsByCapacity(HtmlPage page, String capacity, Locale locale) throws IOException {

        final String modelString = capacity.replace(" ", "").toLowerCase();

        final Optional<HtmlInput> input = page
                .getByXPath("//input[@id='Item2-dimensionCapacity-" + modelString + "']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage pageAfterChoosingCapacity = input.get().click();

        final List<HtmlDivision> divs = pageAfterChoosingCapacity
                .getByXPath("//div[@id='Item3']//div[@class='form-element ']");

        return createProducts(divs, capacity, locale);
    }

    private List<Product> createProducts(List<HtmlDivision> divs, String capacity, Locale locale) {

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
                price = parsePrice(priceSpan.get().getTextContent(), locale);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            products.add(new Product(model, price, Currency.getInstance(locale)));

        });

        return products;
    }

    private String getURL(@PathVariable String countryCode) {
        return String.format(URL, countryCode);
    }

    private Locale getLocale(@PathVariable String countryCode) {
        return new Countries().getLocale(countryCode);
    }

    private CookieManager createCookieManager() {
        return new CookieManager() {
            protected int getPort(URL url) {

                final int port = super.getPort(url);

                return port != -1 ? port : 80;
            }
        };
    }

    private Float parsePrice(String priceString, Locale locale) throws ParseException {

        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        return numberFormat.parse(priceString.trim()).floatValue();
    }
}
