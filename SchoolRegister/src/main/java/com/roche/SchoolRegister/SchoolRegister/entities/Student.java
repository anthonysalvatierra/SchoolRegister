package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name="student")
public class Student extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String dna;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "level", referencedColumnName = "id")
    private Level level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "career", referencedColumnName = "id")
    private Career career;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(dna, student.dna) && Objects.equals(phoneNumber, student.phoneNumber) && Objects.equals(email, student.email) && Objects.equals(status, student.status) && Objects.equals(level, student.level) && Objects.equals(career, student.career);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dna, phoneNumber, email, status, level, career);
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
                ", level=" + level +
                ", career=" + career +
                '}';
    }
}
