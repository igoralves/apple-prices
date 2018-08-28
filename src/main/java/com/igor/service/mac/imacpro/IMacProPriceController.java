package com.igor.service.mac.imacpro;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.igor.model.Product;
import com.igor.service.mac.MacPriceController;

import java.io.IOException;

public abstract class IMacProPriceController extends MacPriceController {

    private static final String MODEL = "iMac Pro";

    protected Product loadIMacPro() throws IOException {
        return getProduct();
    }

    private Product getProduct() throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());

        return createProduct(page, MODEL);
    }
}
