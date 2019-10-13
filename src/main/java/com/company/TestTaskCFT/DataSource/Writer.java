package com.company.TestTaskCFT.DataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer implements AutoCloseable {

    private BufferedWriter writer;

    public Writer(String outFile) {
        try {
            this.writer = new BufferedWriter(new FileWriter(new File(outFile)));
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file or create it");
        }
    }

    public void write(String s) {
        try {
            writer.write(s);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file");
        }


    }

    @Override
    public void close() throws IOException {
        try {
            writer.close();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
