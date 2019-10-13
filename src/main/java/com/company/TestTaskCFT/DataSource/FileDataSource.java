package com.company.TestTaskCFT.DataSource;

import java.io.IOException;

public class FileDataSource implements DataSource  {

    private Reader reader;
    private Writer writer;

    public FileDataSource(String in, String out) {
        this.reader = new Reader(in);
        this.writer = new Writer(out);
    }

    public boolean hasNext() {
        return reader.hasNext();
    }

    public String next() {
        return reader.next();
    }

    public void write(String s) {
        writer.write(s);
    }

    public void close(){
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Error with I/O");
        }
    };
}
