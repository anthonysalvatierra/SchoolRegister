package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "career")
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ind;

    @NotNull
    @Column(unique = true)
    private String name;

    @Max(6)
    @Min(3)
    private Integer duration;

    @ManyToMany
    @JoinColumn(name = "course", referencedColumnName = "id", updatable = false)
    private List<Course> courses;

    public Long getInd() {
        return ind;
    }

    public void setInd(Long ind) {
        this.ind = ind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Career career = (Career) o;
        return Objects.equals(ind, career.ind) && Objects.equals(name, career.name) && Objects.equals(duration, career.duration) && Objects.equals(courses, career.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ind, name, duration, courses);
    }

    @Override
    public String toString() {
        return "Career{" +
                "ind=" + ind +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", courses=" + courses +
                '}';
    }
}
