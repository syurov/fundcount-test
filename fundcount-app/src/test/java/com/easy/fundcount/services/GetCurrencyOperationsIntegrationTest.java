package com.easy.fundcount.services;

import com.easy.fundcount.app.config.SpringContext;
import com.easy.fundcount.dto.Currency;
import com.easy.fundcount.dto.FixerDto;
import com.easy.fundcount.interfaces.GetCurrencyOperations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by syurov on 12/8/2018.
 */
public class GetCurrencyOperationsIntegrationTest {

  private GetCurrencyOperations operations;

  @Before
  public void setUp() throws Exception {
    FixerConfigImpl fixerConfig = new FixerConfigImpl();
    fixerConfig.setAccessKey("cb33a8f009362f42a38af2a21f00ddcc");
    fixerConfig.setUrl("http://data.fixer.io/api/");

    RestTemplate restTemplate = new SpringContext().restTemplate();
    operations = new GetCurrencyOperationsImpl(fixerConfig, restTemplate);
  }

  @Test
  public void getCurrencyLatestTest() throws Exception {

    FixerDto currencyLatest = operations.getCurrencyLatest(Collections.singletonList(Currency.RUB));

    assertNotNull(currencyLatest);
    assertTrue(currencyLatest.getSuccess());
    assertNotNull(currencyLatest.getRates());
    assertEquals(1, currencyLatest.getRates().size());
    assertNotNull(currencyLatest.getRates().get(Currency.RUB));

  }

  @Test
  public void getCurrencyHistoricalTest() throws Exception {

    FixerDto currencyLatest = operations.getCurrencyHistorical(new Date(1512691200000L), Collections.singletonList(Currency.RUB));

    assertNotNull(currencyLatest);
    assertTrue(currencyLatest.getSuccess());
    assertTrue(currencyLatest.getHistorical());
    assertNotNull(currencyLatest.getRates());
    assertEquals(1, currencyLatest.getRates().size());
    assertNotNull(currencyLatest.getRates().get(Currency.RUB));

    assertEquals(69.571078, currencyLatest.getRates().get(Currency.RUB), 0);

  }
}
