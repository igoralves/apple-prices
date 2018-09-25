package com.igor.service.mac.macbookpro;

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
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MacBookProPriceController extends NewMacPriceController {

    private static final String URL = "https://www.apple.com/%s/shop/buy-mac/macbook-pro";

    private static final String DATA_GROUP_13 = "13inch";
    private static final String DATA_GROUP_15 = "15inch";

    private static final String TYPE_13_INCH = "13-inch";
    private static final String TYPE_15_INCH = "15-inch";

    @RequestMapping("/{countryCode}/macbookpro")
    @Cacheable("macbookpro")
    public List<Product> getProducts(@PathVariable String countryCode) throws IOException {

        final List<Product> products = getProducts(getType13Inch(), countryCode);

        products.addAll(getProducts(getType15Inch(), countryCode));

        return products;
    }

    private List<Product> getProducts(String type, String countryCode) throws IOException {

        final Country country = getCountryRepository().getCountry(countryCode);
        final String url = getURL(countryCode);

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(url);
        final String dataGroup;

        if (type.equals(getType13Inch())) {
            dataGroup = DATA_GROUP_13;
        } else if (type.equals(getType15Inch())) {
            dataGroup = DATA_GROUP_15;
        } else {
            throw new RuntimeException("Type does not exist");
        }

        final Optional<HtmlDivision> div = page.getByXPath("//div[@data-group='" + dataGroup + "']")
                .stream()
                .map(HtmlDivision.class::cast)
                .findFirst();

        final List<HtmlDivision> tags = div.get().getByXPath("*//div[@class='as-macbtr-optioncontent']")
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

    private String getType13Inch() {
        return TYPE_13_INCH;
    }

    private String getType15Inch() {
        return TYPE_15_INCH;
    }


}
