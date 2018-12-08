package com.easy.fundcount.app.config;

import com.easy.fundcount.frames.MainFrame;
import com.easy.fundcount.interfaces.FixerFacade;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Configuration of Spring
 */
@Configuration
@ComponentScan("com.easy.fundcount.services")
@PropertySource("classpath:default.properties")
public class SpringContext {

  @Value("${title}")
  String title;

  @Autowired
  private FixerFacade facade;

  @Bean(name = "MainFrame")
  public MainFrame createMainFrame() {
    return new MainFrame(title, facade);
  }

  @Bean()
  public RestTemplate restTemplate() {

    RestTemplate template = new RestTemplate();
    template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    template.getMessageConverters().add(new StringHttpMessageConverter());

    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

    MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
    jsonMessageConverter.setObjectMapper(objectMapper);
    messageConverters.add(jsonMessageConverter);

    template.setMessageConverters(messageConverters);

    return template;
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer setUp() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
