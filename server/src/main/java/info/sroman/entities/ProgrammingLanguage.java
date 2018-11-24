package info.sroman.entities;

import javax.persistence.*;

@Entity
@Table(name = "prog_lang")
public class ProgrammingLanguage {

    @Id
    private Integer progLangId;
    private String name;

    public Integer getProgLangId() {
        return progLangId;
    }

    public void setProgLangId(Integer progLangId) {
        this.progLangId = progLangId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
