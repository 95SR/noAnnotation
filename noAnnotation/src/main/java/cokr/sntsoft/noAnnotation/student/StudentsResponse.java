package cokr.sntsoft.noAnnotation.student;

import java.util.ArrayList;

public class StudentsResponse extends Response {
    private ArrayList<Student> data;
    
    public ArrayList<Student> getData() {
        return data;
    }

    public void setData(ArrayList<Student> studentList) {
        this.data = studentList;
    }
}
