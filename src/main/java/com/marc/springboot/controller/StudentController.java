package com.marc.springboot.controller;

import com.marc.springboot.entity.Student;
import com.marc.springboot.pojo.CreateStudentRequest;
import com.marc.springboot.pojo.InQueryRequest;
import com.marc.springboot.pojo.StudentResponse;
import com.marc.springboot.pojo.UpdateStudentRequest;
import com.marc.springboot.service.StudentService;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Value("${school.name:Default School}")
    private String school;

    @Autowired
    private StudentService studentService;

    /*@RequestMapping("/getStudent")
    public StudentResponse getStudent(){
        return new StudentResponse("1234567890", "Marc Glenn", 40, school);
    }*/

    @RequestMapping("/getAllStudent")
    public List<StudentResponse> getAllStudent(){

        //convert this to java streams
        return studentService.getAllStudents().stream().map(s -> new StudentResponse(s)).toList();
    }

    @GetMapping("/getStudentByFirstName/{firstName}")
    public List<StudentResponse> getStudentByFirstName(@PathVariable String firstName){
        return studentService.getStudentByFirstName(firstName).stream().map(s -> new StudentResponse(s)).toList();
    }

    @GetMapping("/getStudentByFirstNameAndLastName/{firstName}/{lastName}")
    public List<StudentResponse> getStudentByFirstName(@PathVariable("firstName") String firstName,
                                                       @PathVariable("lastName") String lastName){

        return studentService.getStudentByFirstNameAndLastName(firstName, lastName).stream().map(s -> new StudentResponse(s)).toList();
    }

    @GetMapping("/getStudentByFirstIn")
    public List<StudentResponse> getStudentByFirstName(@RequestBody InQueryRequest inQueryRequest){

        return studentService.getStudentByFirstNameIn(inQueryRequest.getFirstNames()).stream().map(s -> new StudentResponse(s)).toList();
    }

    @GetMapping("/getStudentByPage/{pageNumber}/{size}")
    public List<StudentResponse> getStudentByPage(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("size") Integer size){
        return studentService.getStudentPagination(pageNumber, size).stream().map(s -> new StudentResponse(s)).toList();
    }

    @PostMapping("/createStudent")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {

        Student s = studentService.createStudent(createStudentRequest);
        return new StudentResponse(s);
    }

    @RequestMapping("/getAllStudentSorted")
    public List<StudentResponse> getAllStudentSorted(){

        //convert this to java streams
        return studentService.getStudentSorted().stream().map(s -> new StudentResponse(s)).toList();
    }

    @RequestMapping("/getAllStudentFirstNameContains/{firstName}")
    public List<StudentResponse> getAllStudentFirstNameContains(@PathVariable("firstName") String firstName){
        //convert this to java streams
        return studentService.getStudentByFirstNameContains(firstName).stream().map(s -> new StudentResponse(s)).toList();
    }

    @RequestMapping("/getAllStudentFirstNameEndsWith/{firstName}")
    public List<StudentResponse> getAllStudentFirstNameEndsWith(@PathVariable("firstName") String firstName){
        //convert this to java streams
        return studentService.getStudentByFirstNameEndsWith(firstName).stream().map(s -> new StudentResponse(s)).toList();
    }

    @RequestMapping("getStudentByFirstNameLastName/{firstName}/{lastName}")
    public List<StudentResponse> getStudentByFirstNameLastName(@PathVariable("firstName") String firstName,
                                                               @PathVariable("lastName") String lastName){
        //convert this to java streams
        return studentService.getStudentByFirstNameAndLastName(firstName, lastName).stream().map(s -> new StudentResponse(s)).toList();
    }

    @RequestMapping("getStudentByCity/{city}")
    public List<StudentResponse> getStudentByCity(@PathVariable("city") String city){
        //convert this to java streams
        return studentService.getStudentByCity(city).stream().map(s -> new StudentResponse(s)).toList();
    }

    @PostMapping("updateStudentFirstName/{id}/{firstName}")
    public String updateStudentFirstName(@PathVariable("id") Long id, @PathVariable String firstName) {
        Integer rowsUpdated = studentService.updateStudentFirstName(id, firstName);
        return rowsUpdated + " records updated.";
    }

    @PostMapping("/updateStudent")
    public StudentResponse updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {

        Student s = studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(s);
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@RequestParam("id") Long id) {
        return studentService.deleteStudent(id);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudentPv(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping("testGet")
    public String testFunction(){
        studentService.testService();
        return "OK";
    }
}
