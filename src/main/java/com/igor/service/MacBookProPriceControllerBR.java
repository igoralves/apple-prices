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
@RestController
public class MacBookProPriceControllerBR extends MacBookProPriceController {

    public static final String URL = "https://www.apple.com/br/shop/buy-mac/macbook-pro";
    public static final String TYPE_13_INCH = "13-polegadas";
    public static final String TYPE_15_INCH = "15-polegadas";
    public static final Locale LOCALE = new Locale("pt", "BR");

    @RequestMapping("/br/macbookpro")
    public List<Product> loadMacBookPros() throws IOException {
        return super.loadMacBookPros();
    }

    @Override
    protected String getURL() {
        return URL;
    }

    @Override
    protected String getType13Inch() {
        return TYPE_13_INCH;
    }

    @Override
    protected String getType15Inch() {
        return TYPE_15_INCH;
    }

    @Override
    protected Locale getLocale() {
        return LOCALE;
    }

    @Override
    protected Number parsePrice(String priceString) throws ParseException {

        priceString = priceString.trim().replace("R$ ", "");

        NumberFormat nf = NumberFormat.getNumberInstance(LOCALE);

        return nf.parse(priceString);
    }

    public static void main(String[] args) throws IOException {

        final MacBookProPriceControllerBR macBookProPriceControllerBR = new MacBookProPriceControllerBR();

        macBookProPriceControllerBR.loadMacBookPros();
    }
}
