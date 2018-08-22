package com.igor.service.ipad;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IpadPriceControllerPLTest {
    @Test
    public void getProducts() throws Exception {

        final IpadPriceControllerPL controller = new IpadPriceControllerPL();

        final List<Product> products = controller.getProducts();

        Assert.assertEquals(4, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("iPad"));

            Assert.assertTrue(model.contains("32") || model.contains("128"));

            Assert.assertTrue(product.getPrice() > 1000);
            Assert.assertTrue(product.getPrice() < 3000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("PLN"));
        }
    }

}