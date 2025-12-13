package app.ai;

import app.dto.Dto;
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

    public static String generateImageForJournal(JournalEntity journal) throws IOException, InterruptedException {

        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString("""
                    {
                    "model": "dall-e-2",
                    "prompt": "A cute baby sea otter",
                    "n": 1,
                    "size": "1024x1024",
                      "response_format": "b64_json"
                    }
                    """);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + API_IMAGE_PATH))
                .POST(body)
                .header("Authorization", API_TOKEN)
                .build();

        HttpResponse<String> responseCompletableFuture = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        Dto.OpenAIResponse response = mapper.readValue(responseCompletableFuture.body(), Dto.OpenAIResponse.class);

        List<Dto.Base64String> base64Strings = response.data();

        return base64Strings.getFirst().b64_json();
    }

    public static String generateImage(JournalEntity journal) throws IOException, InterruptedException {
        return generateImageForJournal(journal);
    }


}
