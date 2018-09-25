package com.igor.service.mac.imac;

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
public class IMacPriceController extends NewMacPriceController {

    private static final String URL = "https://www.apple.com/%s/shop/buy-mac/imac";

    private static final String DATA_GROUP_21 = "21inch";
    private static final String DATA_GROUP_27 = "27inch";

    private static final String TYPE_21_INCH = "21.5-inch";
    private static final String TYPE_27_INCH = "27-inch";

    @RequestMapping("/{countryCode}/imac")
    @Cacheable("imac")
    public List<Product> getProducts(@PathVariable String countryCode) throws IOException {

        final List<Product> products = getProducts(getType21Inch(), countryCode);

        products.addAll(getProducts(getType27Inch(), countryCode));

        return products;
    }

    private List<Product> getProducts(String type, String countryCode) throws IOException {

        final Country country = getCountryRepository().getCountry(countryCode);
        final String url = getURL(countryCode);

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(url);
        final String dataGroup;

        if (type.equals(getType21Inch())) {
            dataGroup = DATA_GROUP_21;
        } else if (type.equals(getType27Inch())) {
            dataGroup = DATA_GROUP_27;
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

    private String getType21Inch() {
        return TYPE_21_INCH;
    }

    private String getType27Inch() {
        return TYPE_27_INCH;
    }
}
