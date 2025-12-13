
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import java.net.http.HttpClient;


public class test {

    static HttpClient client;
    static String API_TOKEN;
    static String API_URL;
    static String API_IMAGE_PATH;

    static {
        client = HttpClient.newHttpClient();
        API_TOKEN = "Bearer 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";
        API_URL = "https://api.openai.com/v1/";
        API_IMAGE_PATH = "images/generations";
    }
}
