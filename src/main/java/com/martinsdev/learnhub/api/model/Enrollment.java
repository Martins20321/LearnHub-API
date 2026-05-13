package com.martinsdev.learnhub.api.model;

import com.martinsdev.learnhub.api.model.enums.EnrollmentStatus;
import com.martinsdev.learnhub.api.model.pk.EnrollmentPK;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment implements Serializable {

    @EmbeddedId
    private EnrollmentPK id = new EnrollmentPK();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd'T' HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer status;

    public Enrollment(){

    }

    public Enrollment(Course course, User user, Instant moment, EnrollmentStatus status) {
        id.setCourse(course);
        id.setUser(user);
        this.moment = moment;
        setStatus(status);
    }

    public EnrollmentPK getId() {
        return id;
    }

    public void setId(EnrollmentPK id) {
        this.id = id;
    }

    public Course getCourse(){
        return id.getCourse();
    }

    public void setCourse(Course course){
        id.setCourse(course);
    }

    public Instant getMoment() {
        return moment;
    }

    public User getUser(){
        return id.getUser();
    }

    public void setUser(User user){
        id.setUser(user);
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public EnrollmentStatus getStatus() {
        return EnrollmentStatus.valueOf(status);
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status.getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
