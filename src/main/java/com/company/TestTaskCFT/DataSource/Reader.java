package com.company.TestTaskCFT.DataSource;

import java.io.*;

public class Reader implements AutoCloseable {

    private BufferedReader reader;
    private String readedLine;

    public Reader(String sourceFile) {
        try {
            reader = new BufferedReader(new FileReader(new File(sourceFile)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found : " + sourceFile);
        }
    }

    public boolean hasNext() {
        try {
            readedLine = reader.readLine();
            return readedLine != null;
        } catch (Exception e) {
            throw new RuntimeException("Error reading file");
        }
    }

    public String next() {
        return readedLine;
    }

    public void close() throws IOException {
        try {
            reader.close();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
