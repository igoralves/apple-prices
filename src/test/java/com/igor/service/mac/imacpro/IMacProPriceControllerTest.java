package com.igor.service.mac.imacpro;

import com.igor.model.Product;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IMacProPriceControllerTest {

    @Test
    public void getProductsAT() throws Exception {
        testController("at", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsAU() throws Exception {
        testController("au", "AUD", 6000, 8000);
    }

    @Test
    public void getProductsBEFR() throws Exception {
        testController("be-fr", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsBR() throws Exception {
        testController("br", "BRL", 30000, 40000);
    }

    @Test
    public void getProductsCHDE() throws Exception {
        testController("ch-de", "CHF", 5000, 6000);
    }

    @Test
    public void getProductsCZ() throws Exception {
        testController("cz", "CZK", 130000, 150000);
    }

    @Test
    public void getProductsDE() throws Exception {
        testController("de", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsDK() throws Exception {
        testController("dk", "DKK", 40000, 50000);
    }

    @Test
    public void getProductsES() throws Exception {
        testController("es", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsFI() throws Exception {
        testController("fi", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsFR() throws Exception {
        testController("fr", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsHU() throws Exception {
        testController("hu", "HUF", 1700000, 2000000);
    }

    @Test
    public void getProductsIE() throws Exception {
        testController("ie", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsIT() throws Exception {
        testController("it", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsLU() throws Exception {
        testController("lu", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsNL() throws Exception {
        testController("nl", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsNO() throws Exception {
        testController("no", "NOK", 50000, 60000);
    }

    @Test
    public void getProductsPL() throws Exception {
        testController("pl", "PLN", 20000, 30000);
    }

    @Test
    public void getProductsPT() throws Exception {
        testController("pt", "EUR", 5000, 6000);
    }

    @Test
    public void getProductsRU() throws Exception {
        testController("ru", "RUB", 300000, 400000);
    }

    @Test
    public void getProductsSE() throws Exception {
        testController("se", "SEK", 50000, 60000);
    }

    @Test
    public void getProductsTR() throws Exception {
        testController("tr", "TRY", 40000, 50000);
    }

    @Test
    public void getProductsUK() throws Exception {
        testController("uk", "GBP", 4000, 6000);
    }

    @Test
    public void getProductsUS() throws Exception {
        testController("us", "USD", 4000, 6000);
    }


    private void testController(String countryCode, String currency, int rangeMin, int rangeMax) throws IOException {

        final IMacProPriceController controller = new IMacProPriceController();

        final Product product = controller.getProduct(countryCode);

        final String model = product.getModel();

        Assert.assertTrue(model.contains("iMac Pro"));

        Assert.assertTrue(product.getPrice() > rangeMin);
        Assert.assertTrue(product.getPrice() < rangeMax);

        Assert.assertTrue(product.getCurrency().getCurrencyCode().equals(currency));
    }
}