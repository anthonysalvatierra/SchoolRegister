package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "career_course")
public class Career_Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "career", referencedColumnName = "id")
    @Column(name = "id_career", nullable = false)
    private Long id_career;

    @JoinColumn(name = "course", referencedColumnName = "id")
    @Column(name = "id_course", nullable = false)
    private Long id_course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career_Course that = (Career_Course) o;
        return Objects.equals(id_career, that.id_career) && Objects.equals(id_course, that.id_course);
    }

    public Long getId_career() {
        return id_career;
    }

    public void setId_career(Long id_career) {
        this.id_career = id_career;
    }

    public Long getId_course() {
        return id_course;
    }

    public void setId_course(Long id_course) {
        this.id_course = id_course;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_career, id_course);
    }

    @Override
    public String toString() {
        return "Career_Course{" +
                "id_career=" + id_career +
                ", id_course=" + id_course +
                '}';
    }
}
