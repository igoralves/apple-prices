package com.igor.service.ipad;

import com.igor.service.Product;
import com.igor.service.mac.imac.IMacPriceControllerUS;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class IpadPriceControllerUSTest {

    @Test
    public void getProducts() throws Exception {


        final IpadPriceControllerUS controller = new IpadPriceControllerUS();

        final List<Product> products = controller.getProducts();

        Assert.assertEquals(4, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("iPad"));

            Assert.assertTrue(model.contains("32") || model.contains("128"));

            Assert.assertTrue(product.getPrice() > 300);
            Assert.assertTrue(product.getPrice() < 600);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
        }

    }

}