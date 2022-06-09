package com.avvsoft2050.currency_rate_gif;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CurrencyRateGifApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRateGifApplication.class, args);
    }

}
