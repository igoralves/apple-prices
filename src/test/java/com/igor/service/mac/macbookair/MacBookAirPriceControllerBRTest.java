package com.igor.service.mac.macbookair;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by igor on 2018-08-15.
 */
public class MacBookAirPriceControllerBRTest {

    @Test
    public void loadMacBooks() throws Exception {

        final MacBookAirPriceControllerBR controller = new MacBookAirPriceControllerBR();

        final List<Product> products = controller.loadMacBooks();

        Assert.assertEquals(2, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("MacBook Air de 13 polegadas"));

            Assert.assertTrue(product.getPrice() > 7000);
            Assert.assertTrue(product.getPrice() < 9000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("BRL"));
        }
    }
}