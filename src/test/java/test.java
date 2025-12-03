import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class test {

    @Test
    public void test() {
        get("http://localhost:8080/journal/").then().assertThat().statusCode(200);
    }
}
