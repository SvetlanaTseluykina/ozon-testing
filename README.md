Ozon testing
====


## Getting started


Before start you need to have java 8 or above, maven and selenium chromedriver installed.
Before running tests you have to change correctEmail@mail.ru in LoginData class to email that you use to login ozon.ru
Then run the following commands to lauch tests:
```
mvn clean test
```
For allure report run:
```
mvn allure:serve
```
WARNING: these tests are correct for site version on 27/04/2020 and might be different later.