package com.igor.service.imacpro;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.igor.service.MacPriceController;
import com.igor.service.Product;

import java.io.IOException;

/**
 * Created by igor on 2018-08-15.
 */
public abstract class IMacProPriceController extends MacPriceController {

    public static final String MODEL = "iMac Pro";

    protected Product loadIMacPro() throws IOException {
        return getProduct();
    }

    private Product getProduct() throws IOException {

        final WebClient client = getClient();

        final HtmlPage page = client.getPage(getURL());

        return createProduct(page, MODEL);
    }
}
