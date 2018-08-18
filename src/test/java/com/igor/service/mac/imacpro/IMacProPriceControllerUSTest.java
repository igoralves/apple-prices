package com.igor.service.mac.imacpro;

import com.igor.service.Product;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by igor on 2018-08-16.
 */
public class IMacProPriceControllerUSTest {
    @Test
    public void loadIMacPro() throws Exception {

        final IMacProPriceControllerUS controller = new IMacProPriceControllerUS();

        final Product product = controller.loadIMacPro();

        Assert.assertTrue(product.getModel().equals("iMac Pro"));

        Assert.assertTrue(product.getPrice() > 4000);
        Assert.assertTrue(product.getPrice() < 5000);

        Assert.assertTrue(product.getCurrency().getCurrencyCode().equals("USD"));
    }
}