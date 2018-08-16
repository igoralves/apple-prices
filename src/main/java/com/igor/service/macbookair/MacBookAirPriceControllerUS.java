package com.igor.service.macbookair;

import com.igor.service.Product;
import com.igor.service.macbook.MacBookPriceController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by igor on 2018-08-02.
 */
@RestController
public class MacBookAirPriceControllerUS extends MacBookAirPriceController {

    private static final String URL = "https://www.apple.com/shop/buy-mac/macbook-air";
    private static final Locale LOCALE = Locale.US;

    @RequestMapping("/us/macbookair")
    public List<Product> loadMacBooks() throws IOException {
        return super.loadMacBooksAir();
    }

    @Override
    protected String getURL() {
        return URL;
    }

    @Override
    protected Locale getLocale() {
        return LOCALE;
    }
}