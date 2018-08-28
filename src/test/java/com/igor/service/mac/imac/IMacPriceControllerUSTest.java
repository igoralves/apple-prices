package com.igor.service.mac.imac;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IMacPriceControllerUSTest {
    @Test
    public void loadIMacs() throws Exception {

        final IMacPriceControllerUS controller = new IMacPriceControllerUS();

        final List<Product> products = controller.loadIMacs();

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("-inch iMac"));

            Assert.assertTrue(product.getPrice() > 1000);
            Assert.assertTrue(product.getPrice() < 3000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
        }
    }


}