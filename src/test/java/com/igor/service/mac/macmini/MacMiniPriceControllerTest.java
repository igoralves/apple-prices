package com.igor.service.mac.macmini;

import com.igor.model.Product;
import com.igor.service.mac.macbookpro.MacBookProPriceController;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MacMiniPriceControllerTest {

    @Test
    public void getProductsAT() throws Exception {
        testController("at", "EUR", 500, 2000);
    }

    @Test
    public void getProductsAU() throws Exception {
        testController("au", "AUD", 400, 5000);
    }

    @Test
    public void getProductsBEFR() throws Exception {
        testController("be-fr", "EUR", 500, 2000);
    }

    @Test
    public void getProductsBR() throws Exception {
        testController("br", "BRL", 1400, 25000);
    }

    @Test
    public void getProductsCHDE() throws Exception {
        testController("ch-de", "CHF", 500, 2000);
    }

    @Test
    public void getProductsCZ() throws Exception {
        testController("cz", "CZK", 15000, 40000);
    }

    @Test
    public void getProductsDE() throws Exception {
        testController("de", "EUR", 500, 2000);
    }

    @Test
    public void getProductsDK() throws Exception {
        testController("dk", "DKK", 4000, 9000);
    }

    @Test
    public void getProductsES() throws Exception {
        testController("es", "EUR", 500, 2000);
    }

    @Test
    public void getProductsFI() throws Exception {
        testController("fi", "EUR", 500, 2000);
    }

    @Test
    public void getProductsFR() throws Exception {
        testController("fr", "EUR", 500, 2000);
    }

    @Test
    public void getProductsHU() throws Exception {
        testController("hu", "HUF", 100000, 400000);
    }

    @Test
    public void getProductsIE() throws Exception {
        testController("ie", "EUR", 500, 2000);
    }

    @Test
    public void getProductsIT() throws Exception {
        testController("it", "EUR", 500, 2000);
    }

    @Test
    public void getProductsLU() throws Exception {
        testController("lu", "EUR", 500, 2000);
    }

    @Test
    public void getProductsNL() throws Exception {
        testController("nl", "EUR", 500, 2000);
    }

    @Test
    public void getProductsNO() throws Exception {
        testController("no", "NOK", 1100, 11000);
    }

    @Test
    public void getProductsPL() throws Exception {
        testController("pl", "PLN", 2000, 5000);
    }

    @Test
    public void getProductsPT() throws Exception {
        testController("pt", "EUR", 500, 2000);
    }

    @Test
    public void getProductsRU() throws Exception {
        testController("ru", "RUB", 30000, 80000);
    }

    @Test
    public void getProductsSE() throws Exception {
        testController("se", "SEK", 1100, 11000);
    }

    @Test
    public void getProductsTR() throws Exception {
        testController("tr", "TRY", 1100, 10000);
    }

    @Test
    public void getProductsUK() throws Exception {
        testController("uk", "GBP", 400, 1000);
    }

    @Test
    public void getProductsUS() throws Exception {
        testController("us", "USD", 400, 1000);
    }

    private void testController(String countryCode, String currency, int rangeMin, int rangeMax) throws IOException {

        final MacMiniPriceController controller = new MacMiniPriceController();

        final List<Product> products = controller.getProducts(countryCode);

        Assert.assertEquals(3, products.size());

        for (Product product : products) {

            final String model = product.getModel();

            Assert.assertTrue(model.contains("Mac mini"));

            Assert.assertTrue(product.getPrice() > rangeMin);
            Assert.assertTrue(product.getPrice() < rangeMax);

            Assert.assertTrue(product.getCurrency().getCurrencyCode().equals(currency));
        }
    }
}