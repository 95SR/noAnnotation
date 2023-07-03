package cokr.sntsoft.noAnnotation.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

public class StudentRepository {
    private ArrayList<Student> mydata;
    private static StudentRepository INSTANCE;

    private StudentRepository(){
        mydata = CsvReader.studentData();
    }

    public static StudentRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new StudentRepository();
        }

        return INSTANCE;
    }

    public ArrayList<Student> getAllStudents() {
        
        return mydata;
    }

    public Student findStudent(int id) {
        for (Student student : mydata) {
            int studentId = student.getId();
            if (studentId == id) {
                return student;
            }
        }

        return null;
    }

    public Student saveStudent(Student student) {
        Student newStudent = new Student();
        boolean duplicateId=false;
        for (Student item : mydata) {
            if (item.getId() == student.getId()) {
                newStudent = null;
                
                duplicateId=true;
                break;
            } 
        }

        if(!duplicateId){
            mydata.add(student);
            CsvWriter.studentData(student);
            newStudent = student;
            
        }

        System.out.println("repository:" +newStudent);

        return newStudent;

    }

    public boolean removeStudent(int id){
       
    boolean deleted=false;
       Predicate<Student> condition = student -> student.getId()==id;
       deleted = mydata.removeIf(condition);
        CsvWriter.updateStudent(mydata);
       


       return deleted;
    }

    public Student editStudent(Student student){
        Student editedStudent = new Student();
        int id = student.getId();

        for (Student myStudent : mydata) {
            if(myStudent.getId()==id){
                myStudent.setName(student.getName());
                myStudent.setSex(student.getSex());
                myStudent.setAge(student.getAge());
                myStudent.setPhone(student.getPhone());
                myStudent.setLocation(student.getLocation());
                editedStudent = myStudent;
                break;
            }            
        }

         CsvWriter.updateStudent(mydata);


        return editedStudent;
    }


}
