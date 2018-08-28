package com.igor.service.mac.macmini;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading2;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.igor.model.Product;
import com.igor.service.mac.MacPriceController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MacMiniPriceController extends MacPriceController {

    List<Product> getProducts() throws IOException {

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
