package com.avvsoft2050.currency_rate_gif.client;

import com.avvsoft2050.currency_rate_gif.dto.GiphyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy-api", url = "${giphy.url}")
public interface GiphyClient {
    @GetMapping(value = "/search")
    GiphyDto getGif(@RequestParam("api_key") String apiKey,
                    @RequestParam("q") String q,
                    @RequestParam("limit") String limit,
                    @RequestParam("rating") String rating);
}
