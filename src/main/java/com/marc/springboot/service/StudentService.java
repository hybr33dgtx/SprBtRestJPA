package com.marc.springboot.service;

import com.marc.springboot.entity.Address;
import com.marc.springboot.entity.Student;
import com.marc.springboot.pojo.CreateStudentRequest;
import com.marc.springboot.pojo.UpdateStudentRequest;
import com.marc.springboot.repository.AddressRepository;
import com.marc.springboot.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {

        Address address = addressRepository.findByStreetAndCity(createStudentRequest.getStreet(), createStudentRequest.getCity());
        if(address == null) {
            logger.info("Address provided does not exist in Address table. Creating a new entry.");
            address = new Address(createStudentRequest.getStreet(), createStudentRequest.getCity());
            address = addressRepository.save(address);
        }

        Student student = new Student(createStudentRequest);
        logger.info("Student created.");

        student.setAddress(address);
        return studentRepository.save(student);
    }

    public Student updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getId()).get();

        if(updateStudentRequest.getAge() != null) {
            student.setAge(updateStudentRequest.getAge());
        }

        if(updateStudentRequest.getEmail() != null && !updateStudentRequest.getEmail().isEmpty()) {
            student.setEmail(updateStudentRequest.getEmail());
        }

        if(updateStudentRequest.getFirstName() != null && !updateStudentRequest.getFirstName().isEmpty()) {
            student.setFirstName(updateStudentRequest.getFirstName());
        }

        if(updateStudentRequest.getLastName() != null && !updateStudentRequest.getLastName().isEmpty()) {
            student.setLastName(updateStudentRequest.getLastName());
        }

        return studentRepository.save(student);
    }

    public List<Student> getStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public List<Student> getStudentByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<Student> getStudentByFirstNameIn(List<String> strList) {
        return studentRepository.findByFirstNameIn(strList);
    }

    public List<Student> getStudentPagination(Integer pageNumber, Integer size) {
        Pageable pageable = PageRequest.of(pageNumber - 1, size);

        return studentRepository.findAll(pageable).getContent();
    }

    public List<Student> getStudentSorted() {
        Sort sorter = Sort.by(Sort.Direction.ASC, "lastName");

        return studentRepository.findAll(sorter);
    }

    public List<Student> getStudentByFirstNameContains(String substr) {
        return studentRepository.findByFirstNameContains(substr);
    }

    public List<Student> getStudentByFirstNameEndsWith(String substr) {
        return studentRepository.findByFirstNameEndsWith(substr);
    }

    public List<Student> getStudentByCity(String city) {
        return studentRepository.findByAddressCity(city);
    }

    public List<Student> getStudentByFirstLastName(String firstName, String lastName) {
        return studentRepository.getStudentsWithFirstNameLastName(firstName, lastName);
    }

    public Integer updateStudentFirstName(Long id, String firstName) {
        return studentRepository.updateStudentFirstName(id, firstName);
    }

    public String deleteStudent(Long id) {

        boolean studentExist = studentRepository.existsById(id);

        if(studentExist) {
            studentRepository.deleteById(id);
            return "Successfully Deleted.";
        } else {
            return "Record not found.";
        }
    }

    public String testService() {
        Address address = addressRepository.findById(Long.valueOf(1)).get();
        System.out.println(address);
        return "OK";
    }


}
