package com.company.TestTaskCFT.DAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Writer {

    private final String outFile;
    private final List<String> resultList;

    public Writer(String outFile, List<String> resultList) {
        this.outFile = outFile;
        this.resultList = resultList;
    }

    public void write(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outFile)));) {
            for (String s: resultList) {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write in file or create it");
        }
    }
}
