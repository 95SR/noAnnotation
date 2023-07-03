package cokr.sntsoft.noAnnotation.student;


import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

public class CsvWriter {

    public static void studentData(Student student) {
        
       
        try {
            File resource = new ClassPathResource("sample.csv").getFile();
            File file;
            file = ResourceUtils.getFile("classpath:sample.csv");
            //String fileName = "src/main/resources/sample.csv";
            // file = ResourceUtils.getFile("classpath:sample.csv");
            FileWriter writer = new FileWriter(resource, true);
            
            String toCsv = "";
            int id = student.getId();
            String name = student.getName();
            String sex = student.getSex();
            int age = student.getAge();
            String phone = student.getPhone();
            String location = student.getLocation();

            toCsv += "\n" + id + "," + name + "," + sex + "," + age + "," + phone + "," + location;

            writer.write(toCsv);
            writer.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateStudent(ArrayList<Student> students) {
      
        //File file;
        try {
            File resource = new ClassPathResource("sample.csv").getFile();
            File file;
            file = ResourceUtils.getFile("classpath:sample.csv");
            //String fileName = "src/main/resources/sample.csv";
            FileWriter writer = new FileWriter(resource,Charset.forName("UTF-8"));
            String toCsv ="id,이름,성별,나이,전화번호,거주지역";
            
            for (Student student : students) {
                String objString = student.toString();
               
                toCsv+=System.lineSeparator();
                
                toCsv+=objString;
                
            }
            writer.write(toCsv);
           writer.flush();
            
        
       
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
        catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
    }

}
