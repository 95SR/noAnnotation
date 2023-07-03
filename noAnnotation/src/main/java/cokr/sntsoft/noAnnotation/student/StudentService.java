package cokr.sntsoft.noAnnotation.student;

import java.util.ArrayList;

public class StudentService {
    private static StudentService INSTANCE;
    
    
    
    private StudentService(){
       
    }

    public static StudentService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new StudentService();
        }
        return INSTANCE;
    }

    public ArrayList<Student> getAllStudents(){
        StudentRepository repository = StudentRepository.getInstance();
        return repository.getAllStudents();
    }

    public Student findStudent(int id){
        StudentRepository repository = StudentRepository.getInstance();
        return repository.findStudent(id);
    }

    public Student addStudent(Student student){
        StudentRepository repository = StudentRepository.getInstance();
        Student newStudent = repository.saveStudent(student);
        System.out.println("service:" +newStudent);

        return newStudent;
    }

    public boolean removeStudent(int id){
        StudentRepository repository = StudentRepository.getInstance();
        boolean deleted = repository.removeStudent(id);

        return deleted;
    }

    public Student editStudent(Student student){
        StudentRepository repository = StudentRepository.getInstance();
        Student editedStudent = repository.editStudent(student);

        return editedStudent;
    }



}
