package com.avvsoft2050.currency_rate_gif.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateDto {

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private Map<String, Double> rates;

}
