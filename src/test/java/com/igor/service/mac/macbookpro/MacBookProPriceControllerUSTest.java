package com.igor.service.mac.macbookpro;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MacBookProPriceControllerUSTest {

    @Test
    public void loadMacBookPros() throws Exception {

        final MacBookProPriceControllerUS controller = new MacBookProPriceControllerUS();

        final List<Product> products = controller.loadMacBooksPro();

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("-inch MacBook Pro"));

            Assert.assertTrue(product.getPrice() > 1000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
        }
    }
}