package com.avvsoft2050.currency_rate_gif.service;

import com.avvsoft2050.currency_rate_gif.client.GiphyClient;
import com.avvsoft2050.currency_rate_gif.dto.GiphyDto;
import com.avvsoft2050.currency_rate_gif.dto.GiphyResourceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Random;

import static com.avvsoft2050.currency_rate_gif.util.ApplicationConstants.*;

@Service
public class GiphyService {


    @Value("${giphy.api_key}")
    private String giphyApiKey;

    private final GiphyClient giphyClient;

    public GiphyService(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    public String getGifUrl(final boolean isIncreased) {
        Random random = new Random();
        int randomGifNumber = random.nextInt(LIMIT);
        String query = isIncreased ? RICH : BROKE;
        final GiphyDto giphyDto = giphyClient.getGif(giphyApiKey, query, String.valueOf(LIMIT), RATING);
        if (giphyDto == null) {
            throw new IllegalArgumentException(String.format(FAILED_GET_GIF_BY_REQUEST, query));
        }
        final GiphyResourceDto resourceDataDto = giphyDto.getData()[randomGifNumber];
        if (resourceDataDto == null || resourceDataDto.getEmbedUrl().isBlank()) {
            throw new IllegalArgumentException(FAILED_GET_GIF_URL);
        }
        final String url = resourceDataDto.getEmbedUrl();
        return String.format(IFRAME_GIF_URL, url);
    }
}
