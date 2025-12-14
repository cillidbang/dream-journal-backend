package app.Image;

import app.ai.OpenAIService;
import app.dto.RecordCollection;
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
    public Response generateImage(JournalEntity journal) throws IOException, InterruptedException {
        List<RecordCollection.Base64String> base64DataArray = OpenAIService.generateImageForJournal(journal);

        for (RecordCollection.Base64String base64Data : base64DataArray) {
            control.persistImageBase64String(base64Data.b64_json(), journal.id);
        }
        return Response.ok("Image Generated Successfully").build();
    }

    @GET
    @Path("/{id}")
    public Response getAllImages(@PathParam("id") Long id) {
        record Out(List images) {}
        return Response.ok(new Out(control.getAllImagesForJournalId(id))).build();
    }
}
