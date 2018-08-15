package com.igor.service.macbookair;

import com.igor.service.Product;
import com.igor.service.macbook.MacBookPriceControllerUS;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by igor on 2018-08-15.
 */
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