package com.igor.service.mac.macbookair;

import com.igor.model.Product;
import com.igor.service.mac.macbook.MacBookPriceController;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MacBookAirPriceControllerTest {

    @Test
    public void getProductsAT() throws Exception {
        testController("at", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsAU() throws Exception {
        testController("au", "AUD", 1000, 3000);
    }

    @Test
    public void getProductsBEFR() throws Exception {
        testController("be-fr", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsBR() throws Exception {
        testController("br", "BRL", 7000, 10000);
    }

    @Test
    public void getProductsCHDE() throws Exception {
        testController("ch-de", "CHF", 1000, 3000);
    }

    @Test
    public void getProductsCZ() throws Exception {
        testController("cz", "CZK", 30000, 50000);
    }

    @Test
    public void getProductsDE() throws Exception {
        testController("de", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsDK() throws Exception {
        testController("dk", "DKK", 6000, 20000);
    }

    @Test
    public void getProductsES() throws Exception {
        testController("es", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsFI() throws Exception {
        testController("fi", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsFR() throws Exception {
        testController("fr", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsHU() throws Exception {
        testController("hu", "HUF", 300000, 500000);
    }

    @Test
    public void getProductsIE() throws Exception {
        testController("ie", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsIT() throws Exception {
        testController("it", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsLU() throws Exception {
        testController("lu", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsNL() throws Exception {
        testController("nl", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsNO() throws Exception {
        testController("no", "NOK", 10000, 15000);
    }

    @Test
    public void getProductsPL() throws Exception {
        testController("pl", "PLN", 3000, 9000);
    }

    @Test
    public void getProductsPT() throws Exception {
        testController("pt", "EUR", 1000, 2000);
    }

    @Test
    public void getProductsRU() throws Exception {
        testController("ru", "RUB", 70000, 100000);
    }

    @Test
    public void getProductsSE() throws Exception {
        testController("se", "SEK", 10000, 20000);
    }

    @Test
    public void getProductsTR() throws Exception {
        testController("tr", "TRY", 7000, 10000);
    }

    @Test
    public void getProductsUK() throws Exception {
        testController("uk", "GBP", 900, 2000);
    }

    @Test
    public void getProductsUS() throws Exception {
        testController("us", "USD", 900, 2000);
    }

    private void testController(String countryCode, String currency, int rangeMin, int rangeMax) throws IOException {

        final MacBookAirPriceController controller = new MacBookAirPriceController();

        final List<Product> products = controller.getProducts(countryCode);

        Assert.assertEquals(2, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("MacBook"));
            Assert.assertTrue(model.contains("Air"));
            Assert.assertTrue(model.contains("13"));

            Assert.assertTrue(product.getPrice() > rangeMin);
            Assert.assertTrue(product.getPrice() < rangeMax);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals(currency));
        }
    }
}