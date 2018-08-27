package com.igor.service.ipad;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class IpadPriceControllerTest {

    @Test
    public void getProductsBR() throws Exception {

        final IpadPriceController controller = new IpadPriceController();

        final List<Product> products = controller.getProducts("br");

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

    @Test
    public void getProductsPL() throws Exception {

        final IpadPriceController controller = new IpadPriceController();

        final List<Product> products = controller.getProducts("pl");

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

    @Test
    public void getProductsUS() throws Exception {

        final IpadPriceController controller = new IpadPriceController();

        final List<Product> products = controller.getProducts("us");

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