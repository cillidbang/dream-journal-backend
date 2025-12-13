package app.Image;

import app.ai.OpenAIService;
import app.journal.JournalEntity;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;

@Path("/image")
public class ImageBoundary {

    @Inject
    ImageControl control;

    public ImageBoundary() {}

    @Produces(MediaType.TEXT_PLAIN)
    @POST
    public Response generateImage(JournalEntity entity) throws IOException, InterruptedException {
        String base64DataString = "data:image/png;base64, %s".formatted(OpenAIService.generateImage(entity));

        //save image in database for journal
        control.persistImageEntity(base64DataString, entity.id);

        return Response.ok("Image Generated Successfully").build();
    }

    @GET
    @Path("/{id}")
    public Response getAllImages(@PathParam("id") Long id) {
        record Out(List images) {}
        return Response.ok(new Out(control.getImage(id))).build();
    }
}
