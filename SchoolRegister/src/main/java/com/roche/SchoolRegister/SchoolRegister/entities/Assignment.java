package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "It is not allow emptyness")
    @Size(min = 5)
    private String name;

    @Min(1)
    @Max(15)
    private Integer unit;

    @Min(5)
    @Max(75)
    private Integer percent;

    @Temporal(TemporalType.DATE)
    private Date opening;

    @Temporal(TemporalType.DATE)
    private Date closing;

    @JoinColumn(name = "course", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Date getOpening() {
        return opening;
    }

    public void setOpening(Date opening) {
        this.opening = opening;
    }

    public Date getClosing() {
        return closing;
    }

    public void setClosing(Date closing) {
        this.closing = closing;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment assigment = (Assignment) o;
        return Objects.equals(id, assigment.id) && Objects.equals(name, assigment.name) && Objects.equals(unit, assigment.unit) && Objects.equals(percent, assigment.percent) && Objects.equals(opening, assigment.opening) && Objects.equals(closing, assigment.closing) && Objects.equals(course, assigment.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit, percent, opening, closing, course);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit=" + unit +
                ", percent=" + percent +
                ", opening=" + opening +
                ", closing=" + closing +
                ", course=" + course +
                '}';
    }
}
