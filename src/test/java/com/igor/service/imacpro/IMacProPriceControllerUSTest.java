package com.igor.service.imacpro;

import com.igor.service.Product;
import com.igor.service.macbook.MacBookPriceControllerUS;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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