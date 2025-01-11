package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "It must have a name")
    @Size(min = 3, message = "It has to be a correct name")
    private String name;

    @Column(unique = true, nullable = false)
    private String dna;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "It must have a content")
    @Email(message = "The value is required", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+@.[a-zA-Z]{2,}$")
    private String email;

    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_level", referencedColumnName = "id", insertable = false, updatable = false)
    private Level id_level;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Level getId_level() {
        return id_level;
    }

    public void setId_level(Level id_level) {
        this.id_level = id_level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(dna, student.dna) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(email, student.email) && Objects.equals(status, student.status) && Objects.equals(id_level, student.id_level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dna, phoneNumber, email, status, id_level);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dna='" + dna + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", id_level=" + id_level +
                '}';
    }
}
