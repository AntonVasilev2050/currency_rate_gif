package com.avvsoft2050.currency_rate_gif.client;

import com.avvsoft2050.currency_rate_gif.dto.ExchangeRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchange-rate-api", url = "${currency.url}")
public interface ExchangeRateClient {
    @GetMapping(value = "/historical/{date}.json")
    ExchangeRateDto getExchangeRatesForDate(@PathVariable("date") String date,
                                            @RequestParam("app_id") String appID,
                                            @RequestParam("base") String baseCurrency,
                                            @RequestParam("symbols") String symbols);
}

