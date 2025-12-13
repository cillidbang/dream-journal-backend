package app.Image;

import app.ai.OpenAIService;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/image")
public class ImageBoundary {

    public ImageBoundary() {}

    @POST
    public Response generateImage() {
        String base64DataString = "data:image/png;base64, %s".formatted(OpenAIService.getImage());
        return Response.ok(base64DataString).build();
    }
}
