package com.pet.demo.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("exchange")
@Getter
@Setter
public class ExchangeProperties {
    private String ratesUrl;

}
