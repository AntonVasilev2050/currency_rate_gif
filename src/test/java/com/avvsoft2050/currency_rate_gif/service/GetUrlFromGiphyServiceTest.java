package com.avvsoft2050.currency_rate_gif.service;

import com.avvsoft2050.currency_rate_gif.client.ExchangeRateClient;
import com.avvsoft2050.currency_rate_gif.client.GiphyClient;
import com.avvsoft2050.currency_rate_gif.dto.ExchangeRateDto;
import com.avvsoft2050.currency_rate_gif.dto.GiphyDto;
import com.avvsoft2050.currency_rate_gif.dto.GiphyResourceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Map;

import static com.avvsoft2050.currency_rate_gif.util.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetUrlFromGiphyServiceTest {
    @Value("${currency.app_id}")
    private String currencyAppId;

    @Value("${currency.base_currency}")
    private String baseCurrency;

    @Value("${giphy.api_key}")
    private String giphyApiKey;

    @Autowired
    private GetUrlFromGiphyService getUrlFromGiphyService;

    @MockBean
    private ExchangeRateClient exchangeRateClient;

    @MockBean
    private GiphyClient giphyClient;

    private GiphyDto giphyDto;
    private GiphyResourceDto giphyResourceDto;
    private GiphyResourceDto[] giphyResourceDtoArray;
    private ExchangeRateDto todayExchangeRateDto;
    private ExchangeRateDto yesterdayExchangeRateDto;

    @BeforeEach
    public void setUP() {
        giphyResourceDto = new GiphyResourceDto();
        giphyResourceDto.setEmbedUrl("https://giphy.com/embed");
        giphyResourceDtoArray = new GiphyResourceDto[LIMIT];
        for (int i = 0; i < LIMIT; i++){
            giphyResourceDtoArray[i] = giphyResourceDto;
        }
        giphyDto = new GiphyDto();
        giphyDto.setData(giphyResourceDtoArray);
        todayExchangeRateDto = new ExchangeRateDto();
        yesterdayExchangeRateDto = new ExchangeRateDto();
    }

    @AfterEach
    public void tierDown() {
        giphyDto = null;
        giphyResourceDto = null;
        giphyResourceDtoArray = null;
        todayExchangeRateDto = yesterdayExchangeRateDto = null;
    }

    @Test
    void whenGetUrlFromGiphyServiceReturnRichUrlOnGifThen() {

        todayExchangeRateDto.setRates(Map.of(TEST_UE, 65.000));

        yesterdayExchangeRateDto.setRates(Map.of(TEST_UE, 60.000));

        when(exchangeRateClient
                .getExchangeRatesForDate(
                        TODAY,
                        currencyAppId,
                        baseCurrency,
                        TEST_UE))
                .thenReturn(todayExchangeRateDto);

        when(exchangeRateClient
                .getExchangeRatesForDate(
                        YESTERDAY,
                        currencyAppId,
                        baseCurrency,
                        TEST_UE))
                .thenReturn(yesterdayExchangeRateDto);

        when(giphyClient
                .getGif(giphyApiKey, RICH, String.valueOf(LIMIT), RATING))
                .thenReturn(giphyDto);

        var result = getUrlFromGiphyService.responseFromGiphy(TEST_UE);

        assertTrue(result.toString().contains(giphyResourceDtoArray[0].getEmbedUrl()));
    }

    @Test
    void whenGetUrlFromGiphyServiceReturnBrokeUrlOnGifThen() {

        todayExchangeRateDto.setRates(Map.of(TEST_UE, 63.456));

        yesterdayExchangeRateDto.setRates(Map.of(TEST_UE, 65.123));

        when(exchangeRateClient
                .getExchangeRatesForDate(
                        TODAY,
                        currencyAppId,
                        baseCurrency,
                        TEST_UE))
                .thenReturn(todayExchangeRateDto);

        when(exchangeRateClient.
                getExchangeRatesForDate(
                        YESTERDAY,
                        currencyAppId,
                        baseCurrency,
                        TEST_UE))
                .thenReturn(yesterdayExchangeRateDto);

        when(giphyClient
                .getGif(giphyApiKey, BROKE, String.valueOf(LIMIT), RATING))
                .thenReturn(giphyDto);

        var result = getUrlFromGiphyService.responseFromGiphy(TEST_UE);

        assertTrue(result.toString().contains(giphyResourceDtoArray[0].getEmbedUrl()));
    }
}