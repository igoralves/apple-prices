package com.igor.service.ipad;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.igor.xml.Country;
import com.igor.model.Product;
import com.igor.service.CountryRepository;
import com.igor.service.PriceController;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class IpadPriceController extends PriceController {

    private static final String URL = "https://www.apple.com/%s/shop/buy-ipad/ipad-9-7";
    private static final String MODEL_32GB = "32 GB";
    private static final String MODEL_128GB = "128 GB";

    @RequestMapping("/{countryCode}/ipad")
    @Cacheable("ipad")
    public List<Product> getProducts(@PathVariable String countryCode) throws IOException {

        final Country country = getCountryRepository().getCountry(countryCode);
        final String url = getURL(countryCode);

        final HtmlPage firstPage = getFirstPage(url);

        final Optional<HtmlInput> input = firstPage
                .getByXPath("//input[@id='dimensionColor-silver']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage pageAfterChoosingColor = input.get().click();

        final List<Product> products = new ArrayList<>();

        products.addAll(getProductsByCapacity(pageAfterChoosingColor, MODEL_32GB, country));
        products.addAll(getProductsByCapacity(pageAfterChoosingColor, MODEL_128GB, country));

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

    private List<Product> getProductsByCapacity(HtmlPage page, String capacity, Country country) throws IOException {

        final String modelString = capacity.replace(" ", "").toLowerCase();

        final Optional<HtmlInput> input = page
                .getByXPath("//input[@id='Item2-dimensionCapacity-" + modelString + "']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage pageAfterChoosingCapacity = input.get().click();

        final List<HtmlDivision> divs = pageAfterChoosingCapacity
                .getByXPath("//div[@id='Item3']//div[@class='form-element ']");

        return createProducts(divs, capacity, country);
    }

    private List<Product> createProducts(List<HtmlDivision> divs, String capacity, Country country) {

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
                price = parsePrice(priceSpan.get().getTextContent(), country);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            products.add(new Product(model, price, country.getCurrency()));

        });

        return products;
    }

    private String getURL(String countryCode) {
        return String.format(URL, countryCode);
    }

    private CookieManager createCookieManager() {
        return new CookieManager() {
            protected int getPort(URL url) {

                final int port = super.getPort(url);

                return port != -1 ? port : 80;
            }
        };
    }

    private Float parsePrice(String priceString, Country country) throws ParseException {

        final NumberFormat numberFormat = country.getNumberFormat();

        return numberFormat.parse(priceString.trim()).floatValue();
    }
}
