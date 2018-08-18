package com.igor.service.mac.macbook;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MacBookPriceControllerBRTest {
    @Test
    public void loadMacBooks() throws Exception {

        final MacBookPriceControllerBR controller = new MacBookPriceControllerBR();

        final List<Product> products = controller.loadMacBooks();

        Assert.assertEquals(2, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("MacBook de 12 polegadas"));

            Assert.assertTrue(product.getPrice() > 1000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("BRL"));
        }
    }
}