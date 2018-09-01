package com.igor.service.ipad;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class IpadPriceControllerTest {

    @Test
    public void getProductsAT() throws Exception {
        testController("at", "EUR", 300, 600);
    }

    @Test
    public void getProductsAU() throws Exception {
        testController("au", "AUD", 400, 800);
    }

    @Test
    public void getProductsBEFR() throws Exception {
        testController("be-fr", "EUR", 300, 600);
    }

    @Test
    public void getProductsBR() throws Exception {
        testController("br", "BRL", 2400, 4000);
    }

    @Test
    public void getProductsCHDE() throws Exception {
        testController("ch-de", "CHF", 300, 700);
    }

    @Test
    public void getProductsCZ() throws Exception {
        testController("cz", "CZK", 8000, 15000);
    }

    @Test
    public void getProductsDE() throws Exception {
        testController("de", "EUR", 300, 600);
    }

    @Test
    public void getProductsDK() throws Exception {
        testController("dk", "DKK", 2000, 6000);
    }

    @Test
    public void getProductsES() throws Exception {
        testController("es", "EUR", 300, 600);
    }

    @Test
    public void getProductsFI() throws Exception {
        testController("fi", "EUR", 300, 600);
    }

    @Test
    public void getProductsFR() throws Exception {
        testController("fr", "EUR", 300, 600);
    }

    @Test
    public void getProductsHU() throws Exception {
        testController("hu", "HUF", 115000, 200000);
    }

    @Test
    public void getProductsIE() throws Exception {
        testController("ie", "EUR", 300, 600);
    }

    @Test
    public void getProductsIT() throws Exception {
        testController("it", "EUR", 300, 600);
    }

    @Test
    public void getProductsLU() throws Exception {
        testController("lu", "EUR", 300, 600);
    }

    @Test
    public void getProductsNL() throws Exception {
        testController("nl", "EUR", 300, 600);
    }

    @Test
    public void getProductsNO() throws Exception {
        testController("no", "NOK", 3000, 6000);
    }

    @Test
    public void getProductsPL() throws Exception {
        testController("pl", "PLN", 1000, 3000);
    }

    @Test
    public void getProductsPT() throws Exception {
        testController("pt", "EUR", 300, 600);
    }

    @Test
    public void getProductsRU() throws Exception {
        testController("ru", "RUB", 24000, 42000);
    }

    @Test
    public void getProductsSE() throws Exception {
        testController("se", "SEK", 3000, 7000);
    }

    @Test
    public void getProductsTR() throws Exception {
        testController("tr", "TRY", 1000, 4000);
    }

    @Test
    public void getProductsUK() throws Exception {
        testController("uk", "GBP", 300, 600);
    }

    @Test
    public void getProductsUS() throws Exception {
        testController("us", "USD", 300, 600);
    }

    private void testController(String countryCode, String currency, int rangeMin, int rangeMax) throws IOException {

        final IpadPriceController controller = new IpadPriceController();

        final List<Product> products = controller.getProducts(countryCode);

        Assert.assertEquals(4, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("iPad"));

            Assert.assertTrue(model.contains("32") || model.contains("128"));

            Assert.assertTrue(product.getPrice() > rangeMin);
            Assert.assertTrue(product.getPrice() < rangeMax);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals(currency));
        }
    }
}