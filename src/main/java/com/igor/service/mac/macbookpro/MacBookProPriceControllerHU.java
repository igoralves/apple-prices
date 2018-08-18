package com.igor.service.mac.macbookpro;

import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by igor on 2018-08-02.
 */
@RestController
public class MacBookProPriceControllerHU extends MacBookProPriceController {

    private static final String URL = "https://www.apple.com/hu/shop/buy-mac/macbook-pro";
    private static final String TYPE_13_INCH = "13 hüvelykes";
    private static final String TYPE_15_INCH = "15 hüvelykes";
    private static final Locale LOCALE = new Locale("hu", "HU");

    @RequestMapping("/hu/macbookpro")
    public List<Product> loadMacBooksPro() throws IOException {
        return super.loadMacBooksPro();
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
