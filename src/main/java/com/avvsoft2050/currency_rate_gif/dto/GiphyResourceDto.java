package com.avvsoft2050.currency_rate_gif.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GiphyResourceDto {

    @JsonProperty("embed_url")
    private String embedUrl;
}
