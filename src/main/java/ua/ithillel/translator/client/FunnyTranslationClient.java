package ua.ithillel.translator.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.translator.Message;
import ua.ithillel.translator.model.TranslationResult;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FunnyTranslationClient implements TranslatorClient {
    private final static String BASE_URL = "https://api.funtranslations.com/translate/";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public FunnyTranslationClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public synchronized TranslationResult translateIntoPirate(Message message) {
        try {
            // replace whitespaces (" ") with URL encoded value of "%20"
            final String content = message.getContent().replace(" ", "%20");
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + "pirate?text=" + content))
                    .GET()
                    .build();

            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), TranslationResult.class);
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {

        }

        // empty translation result
        return new TranslationResult();
    }
}
