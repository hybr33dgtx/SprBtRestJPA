package com.marc.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marc.springboot.entity.Student;

import java.util.Objects;

public class StudentResponse {

    @JsonProperty("student_id")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    private String street;

    private String city;

    public StudentResponse(Long id, String firstName, String lastName, String email, Integer age, String street, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.street = street;
        this.city = city;
    }

    public StudentResponse(Student s){
        this.id = s.getStudentId();
        this.firstName = s.getFirstName();
        this.lastName = s.getLastName();
        this.age = s.getAge();
        this.email = s.getEmail();

        //add null verification here
        this.street = s.getAddress().getStreet();
        this.city = s.getAddress().getCity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentResponse that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(age, that.age) && Objects.equals(street, that.street) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, age, street, city);
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
