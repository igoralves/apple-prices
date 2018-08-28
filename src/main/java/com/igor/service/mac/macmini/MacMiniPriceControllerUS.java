package com.igor.service.mac.macmini;

import com.igor.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class MacMiniPriceControllerUS extends MacMiniPriceController {

    private static final String URL = "https://www.apple.com/shop/buy-mac/mac-mini";
    private static final Locale LOCALE = Locale.US;

    @RequestMapping("/us/macmini")
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
