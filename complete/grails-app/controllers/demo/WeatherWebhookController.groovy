package demo

import ai.api.model.Fulfillment
import ai.api.web.AIWebhookServlet
import org.grails.apiai.AiWebhookController
import org.grails.web.json.JSONElement

class WeatherWebhookController implements AiWebhookController { // <1>

    YahooWeatherService yahooWeatherService

    /**
     * make sure to remove any auto-generated index methods in this controller, the trait will handle it
     */
    @Override
    void doWebhook(AIWebhookServlet.AIWebhookRequest input, Fulfillment output) {

        String city = input.result.parameters.'geo-city'
        String speech = yahooWeatherService.forecastMessageByCity(city, request.locale)
        output.setSpeech(speech)
        output.setDisplayText(speech)
        //output.contextOut = [] // these are not needed because conversation ends at this point
        //output.data = [:]      // these are not needed because conversation ends at this point
        output.source = 'grails-yahoo-weather'

    }
}
