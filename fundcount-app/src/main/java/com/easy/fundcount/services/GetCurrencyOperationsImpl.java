package com.easy.fundcount.services;

import com.easy.fundcount.dto.FixerDto;
import com.easy.fundcount.exceptions.GetFixerException;
import com.easy.fundcount.interfaces.FixerConfig;
import com.easy.fundcount.interfaces.GetCurrencyOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Getter data from fixer.io
 */
@Component
public class GetCurrencyOperationsImpl implements GetCurrencyOperations {
  private FixerConfig fixerConfig;
  private RestTemplate restTemplate;
  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

  @Autowired
  public GetCurrencyOperationsImpl(FixerConfig fixerConfig, RestTemplate restTemplate) {
    this.fixerConfig = fixerConfig;
    this.restTemplate = restTemplate;
  }

  @Override
  public FixerDto getCurrencyLatest(List<String> symbols) throws GetFixerException {

    UriComponents uriComponents = UriComponentsBuilder
        .fromPath("/latest")
        .queryParam("access_key", fixerConfig.getAccessKey())
        .queryParam("symbols", String.join(",", symbols))
        .build();

    String url = fixerConfig.getUrl() + uriComponents.toUriString();


    ResponseEntity<FixerDto> responseEntity = restTemplate.getForEntity(url, FixerDto.class);

    if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
      return responseEntity.getBody();
    }

    throw new GetFixerException("Http response is " + responseEntity.getStatusCode());
  }

  @Override
  public FixerDto getCurrencyHistorical(Date date, List<String> symbols) throws GetFixerException {

    UriComponents uriComponents = UriComponentsBuilder
        .fromPath("/" + sdf.format(date))
        .queryParam("access_key", fixerConfig.getAccessKey())
        .queryParam("symbols", String.join(",", symbols))
        .build();

    String url = fixerConfig.getUrl() + uriComponents.toUriString();

    ResponseEntity<FixerDto> responseEntity = restTemplate.getForEntity(url, FixerDto.class);

    if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
      return responseEntity.getBody();
    }

    throw new GetFixerException("Http response is " + responseEntity.getStatusCode());
  }
}
