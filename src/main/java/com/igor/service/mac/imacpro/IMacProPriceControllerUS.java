package com.igor.service.mac.imacpro;

import com.igor.service.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by igor on 2018-08-02.
 */
@RestController
public class IMacProPriceControllerUS extends IMacProPriceController {

    private static final String URL = "https://www.apple.com/shop/buy-mac/imac-pro";
    private static final Locale LOCALE = Locale.US;

    @RequestMapping("/us/imac")
    public Product loadIMacPro() throws IOException {
        return super.loadIMacPro();
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
