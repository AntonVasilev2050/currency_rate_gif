package com.avvsoft2050.currency_rate_gif.service;

import com.avvsoft2050.currency_rate_gif.client.ExchangeRateClient;
import com.avvsoft2050.currency_rate_gif.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.avvsoft2050.currency_rate_gif.util.ApplicationConstants.*;

@Service
public class GetUrlFromGiphyService {

    @Value("${currency.app_id}")
    private String currencyAppId;

    @Value("${currency.base_currency}")
    private String baseCurrency;

    private final GiphyService giphyService;

    private final ExchangeRateClient exchangeRateClient;
    public GetUrlFromGiphyService(final GiphyService giphyService, final ExchangeRateClient exchangeRateClient) {
        this.giphyService = giphyService;
        this.exchangeRateClient = exchangeRateClient;
    }

    public ResponseEntity<String> responseFromGiphy(final String symbol) {
        final Double todayValue = getRatesValue(TODAY, symbol);
        final Double yesterdayValue = getRatesValue(YESTERDAY, symbol);
        final boolean isIncreased = (todayValue >= yesterdayValue);
        return ResponseEntity.ok(giphyService.getGifUrl(isIncreased));
    }

    private Double getRatesValue(final String date, final String symbols) {
        final ExchangeRateDto exchangeRateDto = exchangeRateClient
                .getExchangeRatesForDate(date,
                        currencyAppId,
                        baseCurrency,
                        symbols);
        if (exchangeRateDto == null) {
            throw new IllegalArgumentException(String.format(FAILED_GET_EXCHANGE_RATE, symbols, date));
        }
        if (exchangeRateDto.getRates() == null || exchangeRateDto.getRates().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(FAILED_GET_EXCHANGE_RATE, symbols, date));
        }
        return exchangeRateDto.getRates().get(symbols);
    }
}
