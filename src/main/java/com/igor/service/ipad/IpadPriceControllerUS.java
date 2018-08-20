package com.igor.service.ipad;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

// TODO improve method names
// TODO add methods in IpadPriceController


@RestController
public class IpadPriceControllerUS extends IpadPriceController {

    private static final String URL = "https://www.apple.com/shop/buy-ipad/ipad-9-7";
    private static final Locale LOCALE = Locale.US;

    private static final String MODEL_32GB = "32 GB";
    private static final String MODEL_128GB = "128 GB";

    @RequestMapping("/us/ipad")
    public List<Product> getProducts() throws IOException, InterruptedException {

        final WebClient client = getClient();

        client.getOptions().setJavaScriptEnabled(true);

        final HtmlPage page = client.getPage(getURL());

        final Optional<HtmlInput> input1 = page.getByXPath("//input[@id='dimensionColor-silver']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();


        final HtmlPage click = input1.get().click();

        final List<Product> products = new ArrayList<>();

        products.addAll(getProducts(click, MODEL_32GB));
        products.addAll(getProducts(click, MODEL_128GB));

        return products;
    }

    private List<Product> getProducts(HtmlPage page, String capacity) throws IOException {

        final String modelString = capacity.replace(" ", "").toLowerCase();

        final Optional<HtmlInput> input = page.getByXPath("//input[@id='Item2-dimensionCapacity-" + modelString + "']")
                .stream()
                .map(HtmlInput.class::cast)
                .findFirst();

        final HtmlPage click = input.get().click();

        final List<HtmlDivision> divs = click.getByXPath("//div[@id='Item3']//div[@class='form-element ']");

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

    @Override
    protected String getURL() {
        return URL;
    }

    @Override
    protected Locale getLocale() {
        return LOCALE;
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        final List<Product> products = new IpadPriceControllerUS().getProducts();

        products.forEach(System.out::println);
    }
}
