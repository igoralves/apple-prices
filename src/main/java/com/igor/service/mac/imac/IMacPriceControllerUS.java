package com.igor.service.mac.imac;

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
public class IMacPriceControllerUS extends IMacPriceController {

    private static final String URL = "https://www.apple.com/shop/buy-mac/imac";
    private static final String TYPE_21_INCH = "21.5-inch";
    private static final String TYPE_27_INCH = "27-inch";
    private static final Locale LOCALE = Locale.US;

    @RequestMapping("/us/imac")
    public List<Product> loadIMacs() throws IOException {
        return super.loadIMacs();
    }

    @Override
    protected String getURL() {
        return URL;
    }

    @Override
    protected String getType21Inch() {
        return TYPE_21_INCH;
    }

    @Override
    protected String getType27Inch() {
        return TYPE_27_INCH;
    }

    @Override
    protected Locale getLocale() {
        return LOCALE;
    }
}
