package com.avvsoft2050.currency_rate_gif.controller;

import com.avvsoft2050.currency_rate_gif.service.GetUrlFromGiphyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.avvsoft2050.currency_rate_gif.util.ApplicationConstants.TEST_UE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeRateController.class)
class ExchangeRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUrlFromGiphyService getUrlFromGiphyService;

    @Test
    void whenWasCorrectSymbolThenHttpStatus200() throws Exception {

        when(getUrlFromGiphyService
                .responseFromGiphy(TEST_UE))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/rates/" + TEST_UE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenWasInvalidSymbolsThenHttpStatusIs5xx() throws Exception {

        when(getUrlFromGiphyService
                .responseFromGiphy(TEST_UE))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/rates/" + TEST_UE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void whenWasInvalidPageThenHttpStatusIs4xx() throws Exception {

        when(getUrlFromGiphyService
                .responseFromGiphy(TEST_UE))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/rates/" + TEST_UE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}