package com.easy.fundcount.services;

import com.easy.fundcount.interfaces.FixerConfig;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Config for fixer
 */
@PropertySource({
    "classpath:data.fixer.properties"
})
@Data
@Component
public class FixerConfigImpl implements FixerConfig {

  @Value("${accessKey}")
  String accessKey;

  @Value("${url}")
  String url;

  @Override
  public String getAccessKey() {
    return accessKey;
  }

  @Override
  public String getUrl() {
    return url;
  }
}
