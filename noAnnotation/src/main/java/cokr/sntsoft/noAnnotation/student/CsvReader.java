package cokr.sntsoft.noAnnotation.student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

public class CsvReader {
    
     public static ArrayList<Student> studentData() {
        ArrayList<Student> csvdata = new ArrayList<>();

        try {
            File resource = new ClassPathResource("sample.csv").getFile();

            File file;
            file = ResourceUtils.getFile("classpath:sample.csv");
            BufferedReader reader = new BufferedReader(new FileReader(resource,Charset.forName("UTF-8")));
            String line;
            String[] headerSplit;
            HashMap<String,Integer> header_index = new HashMap<>();
            boolean isFirst = true;
            String[] lineSplit;
            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    System.out.println(line +"scvreader");
                    headerSplit = line.split(",");
                    for (int i = 0 ; i < headerSplit.length ; i++) {
                        header_index.put(headerSplit[i], i);
                    }
                    
                    isFirst = false;
                    continue;
                }
                lineSplit = line.split(",");
                System.out.println(line);

                Student student = new Student(Integer.parseInt(lineSplit[0]));
                student.setName(lineSplit[1]);
                student.setSex(lineSplit[2]);
                student.setAge(Integer.parseInt(lineSplit[3]));
                student.setPhone(lineSplit[4]);
                student.setLocation(lineSplit[5]);

                csvdata.add(student);

            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return csvdata;
    }
}
