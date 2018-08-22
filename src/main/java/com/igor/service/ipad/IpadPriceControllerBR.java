package com.igor.service.ipad;

import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class IpadPriceControllerBR extends IpadPriceController {

    private static final String URL = "https://www.apple.com/br/shop/buy-ipad/ipad-9-7";
    private static final Locale LOCALE = new Locale("pt", "BR");

    @RequestMapping("/br/ipad")
    public List<Product> getProducts() throws IOException {
        return super.getProducts();
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
