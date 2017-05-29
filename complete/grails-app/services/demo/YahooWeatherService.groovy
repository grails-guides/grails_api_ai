package demo

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.grails.web.json.JSONElement
import grails.converters.JSON

@CompileStatic
class YahooWeatherService {

	public static final YQL_BASEURL = 'https://query.yahooapis.com/v1/public/yql?q='

	WeatherMessageService weatherMessageService

	@CompileDynamic
	JSONElement forecastByCity(String city) {
		String yqlQuery = """SELECT * FROM weather.forecast WHERE woeid in 
        	(select woeid from geo.places(1) where text='${city}')
		"""
        String yqlUrl = "${YQL_BASEURL}${URLEncoder.encode(yqlQuery as String, 'UTF-8')}&format=json"
		String yqlResponse = yqlUrl.toURL().text
        JSONElement channel = JSON.parse(yqlResponse).query.results.channel
        channel.item.forecast[0] // this is day 1 of a 7 day forecast
	}

	@CompileDynamic
	String forecastMessageByCity(String city, Locale locale) {
		JSONElement forecast = forecastByCity(city)
		String dateStr = forecast.date
		String high = forecast.high
		String low = forecast.low
		String text = forecast.text

		weatherMessageService.forecastMessage(city, dateStr, high, low, text, locale)
	}
}
