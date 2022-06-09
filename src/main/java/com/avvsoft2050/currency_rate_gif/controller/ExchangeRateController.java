package com.avvsoft2050.currency_rate_gif.controller;

import com.avvsoft2050.currency_rate_gif.service.GetUrlFromGiphyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/rates")
public class ExchangeRateController {

    private final GetUrlFromGiphyService getUrlFromGiphyService;

    public ExchangeRateController(GetUrlFromGiphyService getUrlFromGiphyService) {
        this.getUrlFromGiphyService = getUrlFromGiphyService;
    }

    @GetMapping(value = "{symbols}")
    public ResponseEntity<String> getGifByExchangeRateRep(@PathVariable String symbols) {
        return getUrlFromGiphyService.responseFromGiphy(symbols);
    }
}
