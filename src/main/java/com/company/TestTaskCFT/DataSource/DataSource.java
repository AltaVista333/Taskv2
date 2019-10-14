package com.company.TestTaskCFT.DataSource;

import java.util.Iterator;

public interface DataSource extends Iterator<String> {

    boolean hasNext();
    String next();
    void write(String s);
    void close();

}
