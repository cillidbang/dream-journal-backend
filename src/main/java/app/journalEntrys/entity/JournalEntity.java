package app.journalEntrys.entity;


import jakarta.persistence.*;

@Entity
@Table (name = "journalentrysummary")
public class JournalEntity extends BaseEntity {

    @Column(name = "title", columnDefinition = "varchar(255)")
    public String title;

    @Column(name = "subtitle", columnDefinition = "varchar(255)")
    public String subtitle;

    @Column(name = "date", columnDefinition = "varchar(255)")
    public String date;

    @Column(name = "content", columnDefinition = "TEXT")
    public String content;


    public JournalEntity() {
    }
}
