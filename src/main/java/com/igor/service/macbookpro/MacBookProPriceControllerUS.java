package com.igor.service.macbookpro;

import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * Created by igor on 2018-08-02.
 */
@RestController
public class MacBookProPriceControllerUS extends MacBookProPriceController {

    public static final String URL = "https://www.apple.com/shop/buy-mac/macbook-pro";
    public static final String TYPE_13_INCH = "13-inch";
    public static final String TYPE_15_INCH = "15-inch";
    public static final Locale LOCALE = Locale.US;

    @RequestMapping("/us/macbookpro")
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
}
