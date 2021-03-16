package com.kiran.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Utils {


    public Iterator<Object[]> readFromCsv(String filePath) {

        System.out.println();
        List<Object[]> testCases = new ArrayList<>();
        String[] data;
        BufferedReader br;
        String line;

        String csvSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(filePath));
            while((line = br.readLine()) != null) {
                data = line.split(csvSplitBy);
                testCases.add(data);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return testCases.iterator();
    }

    public String getProperty(String key) {
        File config = new File("src/main/resources/config/config.properties");
        String value = null;
        try {
            FileReader reader = new FileReader(config);
            Properties props = new Properties();
            props.load(reader);
            value = props.getProperty(key);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return value;
    }
}
