package entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Organization extends  User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String occupation;
    private LocalDate foundationDate;

    public Organization() {
    }

    public String getName() {
        return name;
    }

    public Organization setName(String name) {
        this.name = name;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public Organization setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public Organization setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[ORGANIZATION] {\n\tID: %d\n\tНаименование:%s;\n\tДата основания: %s;\n\tРод занятий: %s\n\tЛогин:%s\n}",
                super.getId(),this.name,this.foundationDate.toString(),this.occupation,super.getLogin());
    }
}
