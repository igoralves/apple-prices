package com.igor.service.mac.macbookpro;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MacBookProPriceControllerPLTest {

    @Test
    public void loadMacBookPros() throws Exception {

        final MacBookProPriceControllerPL controller = new MacBookProPriceControllerPL();

        final List<Product> products = controller.loadMacBooksPro();

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("-calowy MacBook Pro"));

            Assert.assertTrue(product.getPrice() > 6000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("PLN"));
        }
    }
}