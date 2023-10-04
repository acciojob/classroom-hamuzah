package com.driver;

import java.util.*;

public class StudentRepository {

    HashMap<String,Student>studentDB = new HashMap<>();
    HashMap<String,Teacher>TeacherDB = new HashMap<>();

    private Map<String,ArrayList<String>> teacherStudentPair=new HashMap<String, ArrayList<String>>();

    public void addStudent(Student student) {

        studentDB.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {

        TeacherDB.put(teacher.getName(),teacher);
    }


    public void addStudentTeacherPair(String student, String teacher) {
        ArrayList<String> students=teacherStudentPair.getOrDefault(teacher,new ArrayList<String>());
        students.add(student);
        teacherStudentPair.put(teacher,students);
    }

    public Optional<Student> getstudent(String student) {
        if(studentDB.containsKey(student)){
            return Optional.of(studentDB.get(student));
        }
        return Optional.empty();

    }

    public Optional<Teacher> getTeacher(String teacher) {
        if(TeacherDB.containsKey(teacher)){
            return  Optional.of(TeacherDB.get(teacher));
        }
        return Optional.empty();
    }

    public List<String> getStudentByTeacherName(String teacher) {
           return teacherStudentPair.getOrDefault(teacher,new ArrayList<>());
    }

    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<>();
        for (Student student : studentDB.values()) {
            list.add(student);
        }
        return list;
    }

    public void deleteTeacher(String teacher) {
        TeacherDB.remove(teacher);
        teacherStudentPair.remove(teacher);
    }

    public void deleteStudent(String stud) {
        studentDB.remove(stud);
    }

    public List<String> getAllTeacher() {
        return new ArrayList<>(TeacherDB.keySet());
    }
}
