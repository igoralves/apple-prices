package com.igor.service.mac.imac;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class IMacPriceControllerTest {

    @Test
    public void getProductsAT() throws Exception {
        testController("at", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsAU() throws Exception {
        testController("au", "AUD", 1000, 4000);
    }

    @Test
    public void getProductsBEFR() throws Exception {
        testController("be-fr", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsBR() throws Exception {
        testController("br", "BRL", 9000, 30000);
    }

    @Test
    public void getProductsCHDE() throws Exception {
        testController("ch-de", "CHF", 1000, 3000);
    }

    @Test
    public void getProductsCZ() throws Exception {
        testController("cz", "CZK", 30000, 70000);
    }

    @Test
    public void getProductsDE() throws Exception {
        testController("de", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsDK() throws Exception {
        testController("dk", "DKK", 6000, 30000);
    }

    @Test
    public void getProductsES() throws Exception {
        testController("es", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsFI() throws Exception {
        testController("fi", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsFR() throws Exception {
        testController("fr", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsHU() throws Exception {
        testController("hu", "HUF", 400000, 900000);
    }

    @Test
    public void getProductsIE() throws Exception {
        testController("ie", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsIT() throws Exception {
        testController("it", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsLU() throws Exception {
        testController("lu", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsNL() throws Exception {
        testController("nl", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsNO() throws Exception {
        testController("no", "NOK", 10000, 30000);
    }

    @Test
    public void getProductsPL() throws Exception {
        testController("pl", "PLN", 5000, 12000);
    }

    @Test
    public void getProductsPT() throws Exception {
        testController("pt", "EUR", 1000, 3000);
    }

    @Test
    public void getProductsRU() throws Exception {
        testController("ru", "RUB", 70000, 200000);
    }

    @Test
    public void getProductsSE() throws Exception {
        testController("se", "SEK", 10000, 30000);
    }

    @Test
    public void getProductsTR() throws Exception {
        testController("tr", "TRY", 9000, 30000);
    }

    @Test
    public void getProductsUK() throws Exception {
        testController("uk", "GBP", 1000, 3000);
    }

    @Test
    public void getProductsUS() throws Exception {
        testController("us", "USD", 1000, 3000);
    }

    private void testController(String countryCode, String currency, int rangeMin, int rangeMax) throws IOException {

        final IMacPriceController controller = new IMacPriceController();

        final List<Product> products = controller.getProducts(countryCode);

        Assert.assertEquals(6, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("iMac"));
            Assert.assertTrue(model.contains("21") || model.contains("27"));

            Assert.assertTrue(product.getPrice() > rangeMin);
            Assert.assertTrue(product.getPrice() < rangeMax);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals(currency));
        }
    }
}