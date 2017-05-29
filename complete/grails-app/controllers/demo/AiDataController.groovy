package demo

import ai.api.AIServiceException
import ai.api.model.AIResponse
import org.grails.apiai.AiServiceController
import groovy.transform.CompileStatic

@CompileStatic
class AiDataController implements AiServiceController {

 def index(String query) {
        try {
            AIResponse aiResponse = request(query, request.session)
            response.setContentType("text/plain")
            response.writer.append(aiResponse.result.fulfillment.speech)
        } catch (AIServiceException e) {
            log.error("Error talking to remote service: ${e.message}",e)
        }
    }
}
