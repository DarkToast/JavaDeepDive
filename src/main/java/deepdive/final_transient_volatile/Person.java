package deepdive.final_transient_volatile;

import java.time.LocalDate;

public class Person {

    public final String firstname;
    public final String lastname;
    public final LocalDate birthDate;

    public Person(String firstname, String lastname, LocalDate birthDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
    }

    public Person birthday() {
        return new Person(firstname, lastname,  birthDate.plusYears(1));
    }


    volatile Person person;

    public void set(Person person) {
        this.person = person;
    }

    public void setBirthday(Person person) {
        this.person = person.birthday();
    }

    public Person get() {
        return this.person;
    }

}
