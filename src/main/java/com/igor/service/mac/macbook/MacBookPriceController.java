package com.igor.service.mac.macbook;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.igor.model.Product;
import com.igor.service.mac.NewMacPriceController;
import com.igor.xml.Country;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MacBookPriceController extends NewMacPriceController {

    private static final String URL = "https://www.apple.com/%s/shop/buy-mac/macbook";

    @RequestMapping("/{countryCode}/macbook")
    @Cacheable("macbook")
    public List<Product> getProducts(@PathVariable String countryCode) throws IOException {

        final Country country = getCountryRepository().getCountry(countryCode);
        final String url = getURL(countryCode);

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(url);

        final List<HtmlDivision> tags = page.getByXPath("*//div[@class='as-macbtr-optioncontent']")
                .stream()
                .map(c -> (HtmlDivision) c)
                .filter(c -> ((HtmlDivision) c.getParentNode()).getAttribute("class").contains("modelshown"))
                .collect(Collectors.toList());

        final String modelPrefix = page.getByXPath("*//h2[@class='as-bundleselection-modeltitle']")
                .stream()
                .map(HtmlHeading2.class::cast)
                .findFirst().get().getTextContent().trim();

        return createProducts(tags, modelPrefix, country);
    }

    private String getURL(String countryCode) {
        return String.format(URL, countryCode);
    }
}
