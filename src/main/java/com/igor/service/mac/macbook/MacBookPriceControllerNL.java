package com.igor.service.mac.macbook;

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
public class MacBookPriceControllerNL extends MacBookPriceController {

    private static final String URL = "https://www.apple.com/nl/shop/buy-mac/macbook";
    private static final Locale LOCALE = new Locale("nl", "NL");

    @RequestMapping("/nl/macbook")
    public List<Product> loadMacBooks() throws IOException {
        return super.loadMacBooks();
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
