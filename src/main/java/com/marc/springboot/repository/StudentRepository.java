package com.marc.springboot.repository;

import com.marc.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstNameIn(List<String> stringList);

    List<Student> findByFirstNameContains(String firstName);

    List<Student> findByFirstNameEndsWith(String firstName);

    List<Student> findByAddressCity(String city);

    @Query("From Student where firstName = :firstName and lastName = :lastName")
    List<Student> getStudentsWithFirstNameLastName(String firstName, String lastName);

    @Transactional
    @Modifying
    @Query("Update Student set firstName = :firstName where studentId = :id")
    Integer updateStudentFirstName(Long id, String firstName);
}
