package com.avvsoft2050.currency_rate_gif.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface ApplicationConstants {

    String TODAY = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

   int ONE_DAY = 1;

   String YESTERDAY = LocalDate.now()
            .minusDays(ONE_DAY)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    int LIMIT = 40;

    String RICH = "rich";

    String BROKE = "broke";

    String RATING = "g";

    String TEST_UE = "RUB";

    String FAILED_GET_EXCHANGE_RATE = "Неудачная попытка получить обменный курс %s за %s";

    String FAILED_GET_GIF_BY_REQUEST = "Неудачная попытка получить gif по запросу %s";

    String FAILED_GET_GIF_URL = "Неудачная попытка получить ссылку на gif";

    String IFRAME_GIF_URL = "<iframe src='%s'></iframe>";

}