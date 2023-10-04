package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class StudentRepository {

    private Map<String, Student> students = new HashMap<>(); // for Storing students data
    private Map<String, Teacher> teachers = new HashMap<>();
    private Map<String, List<String>> studentTeacherPairs = new HashMap<>();

    public void add(Student student) {
        students.put(student.getName(), student);
    }

    public Student getStudentByName(String name) {
        return students.get(name);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(students.keySet());
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> studentlist = new ArrayList<>();
        if (studentTeacherPairs.containsKey(teacher))
            studentlist = studentTeacherPairs.get(teacher);
        if (!studentlist.contains(student))
            studentlist.add(student);
        studentTeacherPairs.put(teacher, studentlist);
    }

    public Teacher getTeacherByName(String name) {
        return teachers.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        List<String> studentlist = new ArrayList<>();
        if (studentTeacherPairs.containsKey(teacher))
            studentlist = studentTeacherPairs.get(teacher);
        return studentlist;
    }

    public void deleteTeacherByName(String teacher) {
        List<String> pairlist = new ArrayList<>();
        if (studentTeacherPairs.containsKey(teacher)) {
            pairlist = studentTeacherPairs.get(teacher);
            for (String st : pairlist) {
                students.remove(st);
            }
            studentTeacherPairs.remove(teacher);
        }
        teachers.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String teacher : studentTeacherPairs.keySet()) {
            List<String> pairlist = studentTeacherPairs.get(teacher);
            for (String st : pairlist) {
                if (students.containsKey(st))
                    students.remove(st);
            }
        }
        teachers.clear();
        studentTeacherPairs.clear();
    }
}
