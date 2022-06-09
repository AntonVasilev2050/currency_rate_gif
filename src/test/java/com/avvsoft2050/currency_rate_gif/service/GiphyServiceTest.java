package com.avvsoft2050.currency_rate_gif.service;

import com.avvsoft2050.currency_rate_gif.client.GiphyClient;
import com.avvsoft2050.currency_rate_gif.dto.GiphyDto;
import com.avvsoft2050.currency_rate_gif.dto.GiphyResourceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.avvsoft2050.currency_rate_gif.util.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class GiphyServiceTest {

    @Value("${giphy.api_key}")
    private String giphyApiKey;

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private GiphyService giphyService;

    private GiphyDto giphyDtoRich = new GiphyDto();
    private GiphyDto giphyDtoBroke = new GiphyDto();
    private final GiphyDto giphyDtoBlank = new GiphyDto();
    private GiphyResourceDto giphyResourceDtoRich;
    private GiphyResourceDto giphyResourceDtoBroke;
    private GiphyResourceDto giphyResourceDtoBlank;
    private GiphyResourceDto[] giphyResourceDtoRichArray = new GiphyResourceDto[LIMIT];
    private GiphyResourceDto[] giphyResourceDtoBrokeArray = new GiphyResourceDto[LIMIT];
    private final GiphyResourceDto[] giphyResourceDtoBlankArray = new GiphyResourceDto[LIMIT];

    @BeforeEach
    public void startUP() {
        giphyResourceDtoRich = new GiphyResourceDto();
        giphyResourceDtoRich.setEmbedUrl("rich_url");
        giphyResourceDtoBroke = new GiphyResourceDto();
        giphyResourceDtoBroke.setEmbedUrl("broke_url");
        giphyResourceDtoBlank = new GiphyResourceDto();
        giphyResourceDtoBlank.setEmbedUrl("");
        for (int i = 0; i < LIMIT; i++) {
            giphyResourceDtoRichArray[i] = giphyResourceDtoRich;
            giphyResourceDtoBrokeArray[i] = giphyResourceDtoBroke;
            giphyResourceDtoBlankArray[i] = giphyResourceDtoBlank;
        }
        giphyDtoRich.setData(giphyResourceDtoRichArray);
        giphyDtoBroke.setData(giphyResourceDtoBrokeArray);
        giphyDtoBlank.setData(giphyResourceDtoBlankArray);
    }

    @AfterEach
    public void tierDown() {
        giphyResourceDtoRich = giphyResourceDtoBroke = null;
        giphyDtoRich = giphyDtoBroke = null;
        giphyResourceDtoRichArray = giphyResourceDtoBrokeArray = null;
    }

    @Test
    void whenGetGiphyByRichTagThen() {
        when(giphyClient
                .getGif(giphyApiKey, RICH, String.valueOf(LIMIT), RATING))
                .thenReturn(giphyDtoRich);
        assertTrue(giphyService.getGifUrl(true).contains("rich_url"));
    }

    @Test
    void whenGetGiphyByBrokeTagThen() {
        when(giphyClient
                .getGif(giphyApiKey, BROKE, String.valueOf(LIMIT), RATING))
                .thenReturn(giphyDtoBroke);
        assertTrue(giphyService.getGifUrl(false).contains("broke_url"));
    }

    @Test
    void whenReturnIsBlankUrlThenIllegalArgumentException() {
        when(giphyClient
                .getGif(giphyApiKey, BROKE, String.valueOf(LIMIT), RATING))
                .thenReturn(giphyDtoBlank);
        assertThrows(IllegalArgumentException.class, () -> giphyService.getGifUrl(false));
    }

    @Test
    void whenReturnNullableGiphyDataDtoThenIllegalArgumentException() {
        when(giphyClient
                .getGif(giphyApiKey, BROKE, String.valueOf(LIMIT), RATING))
                .thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> giphyService.getGifUrl(false));
    }
}