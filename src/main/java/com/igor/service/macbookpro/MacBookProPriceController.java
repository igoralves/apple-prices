package com.igor.service.macbookpro;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.igor.service.MacPriceController;
import com.igor.service.Product;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by igor on 2018-08-11.
 */
public abstract class MacBookProPriceController extends MacPriceController {

    public static final String DATA_GROUP_13 = "13inch";
    public static final String DATA_GROUP_15 = "15inch";

    protected List<Product> loadMacBooksPro() throws IOException {

        final List<Product> products = getProducts(getType13Inch());

        products.addAll(getProducts(getType15Inch()));

        return products;
    }

    private List<Product> getProducts(String type) throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());
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

        return createProducts(tags, modelPrefix);
    }

    protected abstract String getType13Inch();

    protected abstract String getType15Inch();


}
