package entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Person extends  User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String secondName;
    private LocalDate birthday;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSecondName() {
        return secondName;
    }

    public Person setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Person setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[PERSON] {\n\tID: %d\n\tПолное имя: [PERSON] %s %s;\n\tДата рождения: %s;\n\tЛогин:%s\n}",
                                    super.getId(),this.firstName,this.secondName,this.birthday.toString(),super.getLogin());
    }
}
