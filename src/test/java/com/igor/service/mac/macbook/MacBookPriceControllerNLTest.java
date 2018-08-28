package com.igor.service.mac.macbook;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MacBookPriceControllerNLTest {
    @Test
    public void loadMacBooks() throws Exception {

        final MacBookPriceControllerNL controller = new MacBookPriceControllerNL();

        final List<Product> products = controller.loadMacBooks();

        Assert.assertEquals(2, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("12-inch MacBook"));

            Assert.assertTrue(product.getPrice() > 1000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("EUR"));
        }
    }
}