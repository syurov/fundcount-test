package com.easy.fundcount.interfaces;

import com.easy.fundcount.exceptions.CalculateProfitException;
import com.easy.fundcount.exceptions.GetFixerException;

import java.util.Date;

/**
 * Created by syurov on 12/8/2018.
 */
public interface FixerFacade {
  double getProfit(Date date, Double amount) throws GetFixerException, CalculateProfitException;
}
