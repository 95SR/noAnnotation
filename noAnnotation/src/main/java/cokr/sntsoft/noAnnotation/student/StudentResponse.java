package cokr.sntsoft.noAnnotation.student;

public class StudentResponse extends Response{
    private Student data;

    public Student getData() {
        return data;
    }

    public void setData(Student student) {
        this.data = student;
    }
    
}
