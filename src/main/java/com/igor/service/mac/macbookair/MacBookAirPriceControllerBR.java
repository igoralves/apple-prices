package com.igor.service.mac.macbookair;

import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class MacBookAirPriceControllerBR extends MacBookAirPriceController {

    private static final String URL = "https://www.apple.com/br/shop/buy-mac/macbook-air";
    private static final Locale LOCALE = new Locale("pt", "BR");

    @RequestMapping("/br/macbookair")
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
