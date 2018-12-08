package com.easy.fundcount.interfaces;

import com.easy.fundcount.dto.FixerDto;
import com.easy.fundcount.exceptions.GetFixerException;

import java.util.Date;
import java.util.List;

/**
 * Created by syurov on 12/8/2018.
 */
public interface GetCurrencyOperations {
  FixerDto getCurrencyLatest(List<String> symbols) throws GetFixerException;

  FixerDto getCurrencyHistorical(Date date, List<String> symbols) throws GetFixerException;
}
