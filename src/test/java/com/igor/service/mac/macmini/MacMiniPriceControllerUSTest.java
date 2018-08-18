package com.igor.service.mac.macmini;

import com.igor.service.Product;
import com.igor.service.mac.macbookair.MacBookAirPriceControllerUS;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by igor on 2018-08-18.
 */
public class MacMiniPriceControllerUSTest {

    @Test
    public void getProducts() throws Exception {

        final MacMiniPriceControllerUS controller = new MacMiniPriceControllerUS();

        final List<Product> products = controller.getProducts();

        Assert.assertEquals(3, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("Mac mini"));

            Assert.assertTrue(product.getPrice() > 400);
            Assert.assertTrue(product.getPrice() < 1000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
        }
    }
}