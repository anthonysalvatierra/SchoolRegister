package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name="teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "It can't be without content")
    @Size(min = 3, max = 10)
    private String name;

    @Column(unique = true, nullable = false)
    @Size(min = 7)
    private String dna;

    @NotEmpty(message = "It can't be without content")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "It must have a content")
    @Email(message = "The value is required", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+@.[a-zA-Z]{2,}$")
    private String email;

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDna(String dna){
        this.dna = dna;
    }

    public String getDna(){
        return this.dna;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name) && Objects.equals(dna, teacher.dna) && Objects.equals(address, teacher.address) && Objects.equals(phoneNumber, teacher.phoneNumber) && Objects.equals(email, teacher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dna, address, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dna='" + dna + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
