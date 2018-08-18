package com.igor.service.mac.macbookpro;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by igor on 2018-08-15.
 */
public class MacBookProPriceControllerHUTest {

    @Test
    public void loadMacBookPros() throws Exception {

        final MacBookProPriceControllerHU controller = new MacBookProPriceControllerHU();

        final List<Product> products = controller.loadMacBooksPro();

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("hüvelykes MacBook Pro"));

            Assert.assertTrue(product.getPrice() > 400000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("HUF"));
        }
    }
}