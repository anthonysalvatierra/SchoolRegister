package com.roche.SchoolRegister.SchoolRegister.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "It is not allow emptyness")
    @Size(min = 5)
    private String name;

    @JoinColumn(name = "teacher", referencedColumnName = "id", insertable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Teacher teacher;

    @JoinColumn(name = "level", referencedColumnName = "id", insertable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Level level;

    @JoinColumn(name = "assignment", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToMany
    private Assignment assignment;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(name, course.name) && Objects.equals(teacher, course.teacher) && Objects.equals(level, course.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacher, level);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                ", level=" + level +
                '}';
    }
}
