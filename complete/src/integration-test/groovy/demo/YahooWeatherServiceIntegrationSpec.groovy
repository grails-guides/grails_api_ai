package demo

import grails.test.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
class YahooWeatherServiceIntegrationSpec extends Specification {

    @Autowired
    YahooWeatherService yahooWeatherService

    def "Yahoo YQL returns weather forecast for a city"() {
        when:
        def jsonForecast = yahooWeatherService.forecastByCity('London')

        then:
        noExceptionThrown()
        jsonForecast
        jsonForecast.date
        jsonForecast.high
        jsonForecast.low
        jsonForecast.text
    }

    def "forecastMessageByCity fetches forecast from Yahooo and use message code"() {
        when:
        String msg = yahooWeatherService.forecastMessageByCity('London', Locale.default)

        then:
        msg
        msg.startsWith('The weather in')
    }
}
