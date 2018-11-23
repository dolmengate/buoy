package info.sroman.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "prog_lang")
public class ProgrammingLanguage {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "content_id")
    private String progLangId;
    private String name;

    public String getProgLangId() {
        return progLangId;
    }

    public void setProgLangId(String progLangId) {
        this.progLangId = progLangId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
