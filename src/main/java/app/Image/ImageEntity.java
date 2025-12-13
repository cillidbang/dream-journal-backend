package app.Image;

import app.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_base64", columnDefinition = "TEXT")
    String fileBase64;

    @Column(name = "journal_id")
    Long journalId;

    public ImageEntity() {}

    public ImageEntity(String fileName, String fileBase64, Long journalId) {
        this.fileName = fileName;
        this.fileBase64 = fileBase64;
        this.journalId = journalId;
    }
}
