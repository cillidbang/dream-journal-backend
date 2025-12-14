package app.Image;

import app.util.UngenericCRUD;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class ImageControl {

    @Inject
    UngenericCRUD crud;

    public List getAllImagesForJournalId(Long journalId) {
        return crud.getImagesForJournalId(journalId);
    }


    public Optional<ImageEntity> persistImageBase64String(String base64String, Long jorunalId) throws IOException {
        String fileName = "image_%s.%s".formatted(LocalDateTime.now().toLocalTime().getNano(), jorunalId);
        ImageEntity imageEntity = new ImageEntity(fileName, base64String, jorunalId);
        crud.create(imageEntity);
        return Optional.of(imageEntity);
    }
}
