package com.igor.service.mac.imac;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.igor.service.Product;
import com.igor.service.mac.MacPriceController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class IMacPriceController extends MacPriceController {

    private static final String DATA_GROUP_21 = "21inch";
    private static final String DATA_GROUP_27 = "27inch";

    protected List<Product> loadIMacs() throws IOException {


        final List<Product> products = getProducts(getType21Inch());

        products.addAll(getProducts(getType27Inch()));

        return products;
    }

    private List<Product> getProducts(String type) throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());
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

        return createProducts(tags, modelPrefix);
    }

    protected abstract String getType21Inch();

    protected abstract String getType27Inch();
}
