package com.igor.service.mac.macmini;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MacMiniPriceControllerBRTest {

    @Test
    public void getProducts() throws Exception {

        final MacMiniPriceControllerBR controller = new MacMiniPriceControllerBR();

        final List<Product> products = controller.getProducts();

        Assert.assertEquals(3, products.size());

        for (Product product : products) {

            Assert.assertTrue(product.getModel().contains("Mac mini"));

            Assert.assertTrue(product.getPrice() > 3000);
            Assert.assertTrue(product.getPrice() < 8000);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("BRL"));
        }
    }
}