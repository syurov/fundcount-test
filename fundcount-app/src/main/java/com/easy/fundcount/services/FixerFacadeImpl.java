package com.easy.fundcount.services;

import com.easy.fundcount.dto.Currency;
import com.easy.fundcount.dto.FixerDto;
import com.easy.fundcount.exceptions.CalculateProfitException;
import com.easy.fundcount.exceptions.GetFixerException;
import com.easy.fundcount.interfaces.FixerFacade;
import com.easy.fundcount.interfaces.GetCurrencyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

/**
 * Facade
 */
@Service
public class FixerFacadeImpl implements FixerFacade {

  GetCurrencyOperations operations;

  @Autowired
  public FixerFacadeImpl(GetCurrencyOperations operations) {
    this.operations = operations;
  }

  @Override
  public double getProfit(Date date, Double amount) throws GetFixerException, CalculateProfitException {
    FixerDto rubLatest = operations.getCurrencyLatest(Collections.singletonList(Currency.RUB));
    FixerDto rubHistorical = operations.getCurrencyHistorical(date, Collections.singletonList(Currency.RUB));

    if (Currency.EUR.equals(rubLatest.getBase())
        && Currency.EUR.equals(rubHistorical.getBase())
        && rubLatest.getRates().size() > 0
        && rubHistorical.getRates().size() > 0
        ) {
      Double latestRate = rubLatest.getRates().get(Currency.RUB);
      Double historicalRate = rubHistorical.getRates().get(Currency.RUB);

      if (latestRate != null && historicalRate != null) {

        double profit = (latestRate - historicalRate) * amount;

        profit -= profit * 0.005;

        return profit;
      }
    }

    throw new CalculateProfitException();
  }
}
