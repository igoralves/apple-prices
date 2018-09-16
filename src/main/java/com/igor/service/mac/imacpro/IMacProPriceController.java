package com.igor.service.mac.imacpro;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.igor.model.Product;
import com.igor.service.CountryRepository;
import com.igor.service.mac.NewMacPriceController;
import com.igor.xml.Country;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IMacProPriceController extends NewMacPriceController {

    private static final String URL = "https://www.apple.com/%s/shop/buy-mac/imac-pro";

    private static final String MODEL = "iMac Pro";

    @RequestMapping("/{countryCode}/imacpro")
    @Cacheable("imacpro")
    public Product getProduct(@PathVariable String countryCode) throws IOException {

        final Country country = new CountryRepository().getCountry(countryCode);
        final String url = getURL(countryCode);

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(url);

        return createProduct(page, MODEL, country);
    }

    private String getURL(String countryCode) {
        return String.format(URL, countryCode);
    }
}
