package crudApp.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private byte age;

    public User() {
    }

    public User(String firstName, String lastName, byte age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(long id, String firstName, String lastName, byte age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User " + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ' ';
    }
}
