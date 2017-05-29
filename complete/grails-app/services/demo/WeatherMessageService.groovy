package demo

import groovy.transform.CompileStatic
import org.springframework.context.MessageSource

@CompileStatic
class WeatherMessageService {

    MessageSource messageSource

    String forecastMessage(String city, String dateStr, String high, String low, String text, Locale locale) {
        Object[] args = [city, dateStr, high, low, text] as Object[]
        messageSource.getMessage('weather.forecast', args, locale)
    }
}