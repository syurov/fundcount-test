package com.easy.fundcount.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * Dto of response of fixer.io
 */
@Data
public class FixerDto {
  private Boolean success;
  private Boolean historical;
  private Long timestamp;
  private String base;
  private Date date;
  private Map<String, Double> rates;
}
