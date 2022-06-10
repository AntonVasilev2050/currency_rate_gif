# currency_rate_gif

Сервис для запроса курсов валют с сервера и отображение gif картинки.

если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich
если ниже - отсюда https://giphy.com/search/broke
Ссылки
REST API курсов валют - https://docs.openexchangerates.org/
REST API гифок - https://developers.giphy.com/docs/api#quick-start-guide
#### Инструкции по сборке и запуску

Сборка: 
gradle clean build

Запуск: 
java -jar build/libs/currency_rate_gif-0.0.1-SNAPSHOT.jar 

**Запуск в Docker:**
docker build -t currency_rate_gif:1.0 .

docker run --name currency_rate_gif -d -p 8080:8080 -t currency_rate_gif:1.0

После запуска приложения или контейнера Docker переходим в 
браузер и набираем:
localhost:8080/api/v1/rates/{symbols}

{symbols} - код валюты.
 
Пример localhost:8080/api/v1/rates/RUB
