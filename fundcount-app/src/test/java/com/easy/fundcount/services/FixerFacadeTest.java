package com.easy.fundcount.services;

import com.easy.fundcount.dto.Currency;
import com.easy.fundcount.dto.FixerDto;
import com.easy.fundcount.interfaces.FixerFacade;
import com.easy.fundcount.interfaces.GetCurrencyOperations;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;

import java.util.Collections;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by syurov on 12/8/2018.
 */
public class FixerFacadeTest {

  private FixerFacade facade;

  private Date date;

  @Before
  public void setUp() throws Exception {
    GetCurrencyOperations operations = BDDMockito.mock(GetCurrencyOperations.class);

    FixerDto dto = new FixerDto();
    dto.setBase(Currency.EUR);
    dto.setSuccess(true);
    dto.setRates(Collections.singletonMap(Currency.RUB, 69.571078));
    when(operations.getCurrencyLatest(any())).thenReturn(dto);

    date = new Date(1512691200000L);

    dto = new FixerDto();
    dto.setBase(Currency.EUR);
    dto.setSuccess(true);
    dto.setRates(Collections.singletonMap(Currency.RUB, 55.545645));
    when(operations.getCurrencyHistorical(any(), any())).thenReturn(dto);

    facade = new FixerFacadeImpl(operations);
  }

  @Test
  public void getProfitTest() throws Exception {
    double profit = facade.getProfit(date, 100.0);
    assertEquals(1395.5305835, profit, 0.0000001);
  }

}
