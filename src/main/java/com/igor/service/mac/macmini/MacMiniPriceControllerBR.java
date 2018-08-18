package com.igor.service.mac.macmini;

import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class MacMiniPriceControllerBR extends MacMiniPriceController {

    private static final String URL = "https://www.apple.com/br/shop/buy-mac/mac-mini";
    private static final Locale LOCALE = new Locale("pt", "BR");

    @RequestMapping("/br/macmini")
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
