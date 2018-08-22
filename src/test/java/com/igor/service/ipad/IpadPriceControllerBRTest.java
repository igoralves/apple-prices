package com.igor.service.ipad;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IpadPriceControllerBRTest {
    @Test
    public void getProducts() throws Exception {

        final IpadPriceControllerBR controller = new IpadPriceControllerBR();

        final List<Product> products = controller.getProducts();

        Assert.assertEquals(4, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("iPad"));

            Assert.assertTrue(model.contains("32") || model.contains("128"));

            Assert.assertTrue(product.getPrice() > 2400);
            Assert.assertTrue(product.getPrice() < 4000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("BRL"));
        }
    }
}