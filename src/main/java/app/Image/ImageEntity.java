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

    @Column(name = "file_path")
    String filePath;

    @Column(name = "journal_id")
    Long journalId;

    public ImageEntity() {}

    public ImageEntity(String fileName, String filePath, Long journalId) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.journalId = journalId;
    }
}
