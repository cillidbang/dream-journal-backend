package app.ai;

import app.dto.RecordCollection;
import app.journal.JournalEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class OpenAIService {

    static HttpClient client;
    static String API_TOKEN;
    static String API_URL;
    static String API_IMAGE_PATH;

    static {
        client = HttpClient.newHttpClient();
        API_TOKEN = "";
        API_URL = "https://api.openai.com/v1/";
        API_IMAGE_PATH = "images/generations";
    }

    public static List<RecordCollection.Base64String> generateImageForJournal(JournalEntity journal) throws IOException, InterruptedException {

        String prompt = """ 
                Aufgabe: Du generiest ein Bild auf Basis meiner Traumbeschreibung, stilistisch im 'TraumStyle'. 
                Traumüberschrift: %s
                Traumbeschreibung: %s
                """.formatted(journal.title, journal.content);

        //replace newlines for valid json.
        prompt = prompt.replaceAll("\\n", "");

        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString("""
                    {
                    "model": "dall-e-2",
                    "prompt": "%s",
                    "n": 1,
                    "size": "512x512",
                      "response_format": "b64_json"
                    }
                    """.formatted(prompt));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + API_IMAGE_PATH))
                .POST(body)
                .header("Authorization", API_TOKEN)
                .build();

        HttpResponse<String> responseCompletableFuture = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        RecordCollection.OpenAIResponse response = mapper.readValue(responseCompletableFuture.body(), RecordCollection.OpenAIResponse.class);

        return response.data();

    }


}
