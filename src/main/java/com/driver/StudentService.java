package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StudentService {

    @Autowired
    StudentRepository  studentRepository;
    public void addStudent(Student student) {

        studentRepository.addStudent(student);

    }

    public void addTeacher(Teacher teacher) {

        studentRepository.addTeacher(teacher);
    }


    public void addStudentTeacherPair(String student, String teacher) {
        Optional<Student> studentOptional = studentRepository.getstudent(student);
        Optional<Teacher> teacherOptional = studentRepository.getTeacher(teacher);

        if(studentOptional.isEmpty()){
            throw new RuntimeException("student not present in the database");
        }
        if(teacherOptional.isEmpty()){
            throw new RuntimeException("teacher not present in the database");
        }
        Teacher teacherObj=teacherOptional.get();
        teacherObj.setNumberOfStudents(teacherObj.getNumberOfStudents()+1);
        studentRepository.addTeacher(teacherObj);
        studentRepository.addStudentTeacherPair(student,teacher);


    }

    public Student studentByName(String name) {
        Optional<Student> studentsOpt= studentRepository.getstudent(name);
        if(studentsOpt.isPresent()) {
            return studentsOpt.get();
        }
        throw new RuntimeException("student not present");
        
    }

    public Teacher teacherByName(String name) {
        Optional<Teacher> teacherOpt= studentRepository.getTeacher(name);
        if(teacherOpt.isPresent()) {
            return teacherOpt.get();
        }
        throw new RuntimeException("teacher not present");
    }

    public List<String> getStudentByTeacherName(String teacher) {
        return studentRepository.getStudentByTeacherName(teacher);
    }

    public List<String> getAllStudent() {
        return studentRepository.getAllStudent();
    }


    public void deleteTeacherByName(String teacher) {
        List<String> students=getStudentByTeacherName(teacher);
        studentRepository.deleteTeacher(teacher);
        for(String stud:students){
            studentRepository.deleteStudent(stud);
        }
    }

    public void deleteAllTeacher() {
        List<String> teacher=studentRepository.getAllTeacher();
        for(String tech:teacher){
            deleteTeacherByName(tech);
        }
    }
}
