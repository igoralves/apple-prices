package com.igor.service.macbook;

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
public abstract class MacBookPriceController extends MacPriceController {

    protected List<Product> loadMacBooks() throws IOException {
        return getProducts();
    }

    private List<Product> getProducts() throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());

        final List<HtmlDivision> tags = page.getByXPath("*//div[@class='as-macbtr-optioncontent']")
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
}
