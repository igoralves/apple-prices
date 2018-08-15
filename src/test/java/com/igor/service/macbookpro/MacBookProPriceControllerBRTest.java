package com.igor.service.macbookpro;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by igor on 2018-08-15.
 */
public class MacBookProPriceControllerBRTest {

    @Test
    public void loadMacBookPros() throws Exception {

        final MacBookProPriceControllerBR controller = new MacBookProPriceControllerBR();

        final List<Product> products = controller.loadMacBooksPro();

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            // TODO replace with regex
            Assert.assertTrue(product.getModel().contains("MacBook Pro de"));
            Assert.assertTrue(product.getModel().contains("polegadas"));

            Assert.assertTrue(product.getPrice() > 10000);
            Assert.assertTrue(product.getPrice() < 30000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("BRL"));
        }
    }
}