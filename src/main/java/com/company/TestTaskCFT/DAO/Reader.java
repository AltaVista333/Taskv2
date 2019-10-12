package com.company.TestTaskCFT.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;


public class Reader implements Iterator<String>, AutoCloseable {

    private BufferedReader reader;
    private String readedLine;

    public Reader(String sourceFile) {
        try {
            reader = new BufferedReader(new FileReader(new File(sourceFile)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found : " + sourceFile);
        }

    }

    @Override
    public boolean hasNext() {
        try {
            readedLine = reader.readLine();
            if (readedLine == null) {
                close();
            }
            return readedLine != null;
        } catch (Exception e) {
            throw new RuntimeException("Error reading file");
        }
    }

    @Override
    public String next() {
        return readedLine;
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
