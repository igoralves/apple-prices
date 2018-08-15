package com.igor.service.imac;

import com.igor.service.Product;
import com.igor.service.macbookpro.MacBookProPriceControllerUS;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by igor on 2018-08-15.
 */
public class IMacPriceControllerUSTest {
    @Test
    public void loadIMacs() throws Exception {

        final IMacPriceControllerUS controller = new IMacPriceControllerUS();

        final List<Product> products = controller.loadIMacs();

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("inch"));

            Assert.assertTrue(product.getPrice() > 1000);
            Assert.assertTrue(product.getPrice() < 3000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
        }
    }


}