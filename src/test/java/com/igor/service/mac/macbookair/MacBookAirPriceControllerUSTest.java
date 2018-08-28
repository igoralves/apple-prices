package com.igor.service.mac.macbookair;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MacBookAirPriceControllerUSTest {

    @Test
    public void loadMacBooks() throws Exception {

        final MacBookAirPriceControllerUS controller = new MacBookAirPriceControllerUS();

        final List<Product> products = controller.loadMacBooks();

        Assert.assertEquals(2, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("13-inch MacBook Air"));

            Assert.assertTrue(product.getPrice() > 100);
            Assert.assertTrue(product.getPrice() < 2000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
        }
    }
}